(ns rex-sheridan.taklo.cards
   (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :cards))

(defn create-new-card [id-list & [opts]]
  (request :post "cards" (merge {:idList id-list} opts)))

(defn get-card [card-id & [opts]] 
  (request :get (path card-id) opts))

(defn update-card [card-id & [opts]] 
  (request :put (path card-id) opts))

(defn delete-card [card-id] 
  (request :delete (path card-id)))

(defn get-field-on-card [card-id field] 
  (request :get (path card-id field)))

(defn get-actions-on-card [card-id & [opts]] 
  (request :get (path card-id :actions) opts))

(defn get-attachments-on-card [card-id fields filter & [opts]] 
  (request :get (path card-id :attachments) (merge {:fields fields
                                                        :filter filter} opts)))

;; TODO need to attach files as multi-part form data
;; https://developer.atlassian.com/cloud/trello/rest/api-group-cards/#api-cards-id-attachments-post
(defn create-attachment-on-card [card-id & [opts]] 
  (request :post (path card-id :attachments) opts))

#_(
   

;; Multipart form uploads/posts
;; takes a vector of maps, to preserve the order of entities, :name
;; will be used as the part name unless :part-name is specified
(client/post "http://example.org" {:multipart [{:name "title" :content "My Awesome Picture"}
                                               {:name "Content/type" :content "image/jpeg"}
                                               {:name "foo.txt" :part-name "eggplant" :content "Eggplants"}
                                               {:name "file" :content (clojure.java.io/file "pic.jpg")}]
                                   ;; You can also optionally pass a :mime-subtype
                                   :mime-subtype "foo"})

;; Multipart :content values can be one of the following:
;; String, InputStream, File, a byte-array, or an instance of org.apache.http.entity.mime.content.ContentBody
;; Some Multipart bodies can also support more keys (like :encoding
;; and :mime-type), check src/clj-http/multipart.clj to see all flags

;; Apache's http client automatically retries on IOExceptions, if you
;; would like to handle these retries yourself, you can specify a
;; :retry-handler. Return true to retry, false to stop trying:
(client/post "http://example.org" {:multipart [["title" "Foo"]
                                               ["Content/type" "text/plain"]
                                               ["file" (clojure.java.io/file "/tmp/missing-file")]]
                                   :retry-handler (fn [ex try-count http-context]
                                                    (println "Got:" ex)
                                                    (if (> try-count 4) false true))})

)
;; TODO Validate downloading attachments works
(defn get-attachment-on-card [card-id attachment-id & [opts]] 
  (request :get (path card-id :attachments attachment-id) opts))

(defn delete-attachment-on-card [card-id attachment-id]
  (request :delete (path card-id :attachments attachment-id)))

(defn get-board-for-card [card-id & [opts]] 
  (request :get (path card-id :board) opts))

(defn get-check-items-on-card [card-id & [opts]] 
  (request :get (path card-id :checkItemStates) opts))

(defn get-checklists-on-card [card-id & [opts]] 
  (request :get (path card-id :checklists) opts))

(defn create-checklist-on-card [card-id & [opts]] 
  (request :post (path card-id :checklists) opts))

(defn get-check-item-on-card [card-id check-item-id & [opts]] 
  (request :get (path card-id :checkItem check-item-id) opts))

(defn update-check-item-on-card [card-id check-item-id & [opts]] 
  (request :put (path card-id :checkItem check-item-id) opts))

(defn delete-check-item-on-card [card-id check-item-id & [opts]] 
  (request :delete (path card-id :checkItem check-item-id) opts))

(defn get-the-list-of-card [card-id & [opts]] 
  (request :get (path card-id :list) opts))

(defn get-the-members-of-card [card-id & [opts]] 
  (request :get (path card-id :members) opts))

(defn get-members-who-have-voted-on-card [card-id & [opts]] 
  (request :get (path card-id :membersVoted) opts))

(defn add-member-vote-to-card [card-id member-id & [opts]] 
  (request :post (path card-id :membersVoted) (merge {:value member-id} opts)))

(defn get-plugin-data-on-card [card-id] 
  (request :get (path card-id :pluginData)))

(defn get-stickers-on-card [card-id & [opts]] 
  (request :get (path card-id :stickers) opts))

(defn add-sticker-to-card [card-id image top left z-index & [opts]] 
  (request :post (path card-id :stickers)
           (merge
            {:image image
             :top top
             :left left
             :zIndex z-index}
            opts)))

(defn get-sticker-on-card [card-id sticker-id & [opts]] 
  (request :get (path card-id :stickers sticker-id) opts))

(defn update-sticker-on-card [card-id sticker-id top left z-index & [opts]] 
  (request :put (path card-id :stickers sticker-id)
           (merge
            {:top top
             :left left
             :zIndex z-index}
            opts)))

(defn delete-sticker-on-card [card-id sticker-id] 
  (request :delete (path card-id :stickers sticker-id)))

(defn update-comment-action-on-card [card-id action-id text] 
  (request :put (path card-id :actions action-id :comments) {:text text}))

(defn delete-comment-on-card [card-id action-id] 
  (request :delete (path card-id :actions action-id :comments)))

;; TODO check this works
(defn update-custom-field-item-on-card [card-id custom-field-id value] 
  (request :put (path card-id :customField custom-field-id :item) value {}))

(defn get-custom-field-items-for-card [card-id] 
  (request :get (path card-id :customFieldItems)))

(defn add-new-comment-to-card [card-id text] 
  (request :post (path card-id :actions :comments) {:text text}))

(defn add-label-to-card [card-id & [opts]] 
  (request :post (path card-id :idLabels) opts))

(defn add-member-to-card [card-id member-id]
  (request :post (path card-id :idMembers) {:value member-id}))

(defn create-new-label-on-card [card-id color & [opts]] 
  (request :post (path card-id :labels)
           (merge {:color color} opts)))

(defn mark-card-s-notifications-as-read [card-id] 
  (request :post (path card-id :markAssociatedNotificationsRead)))

(defn remove-label-from-card [card-id label-id] 
  (request :delete (path card-id :idLabels label-id)))

(defn remove-member-from-card [card-id member-id] 
  (request :delete (path card-id :idMembers member-id)))

(defn remove-members-vote-on-card [card-id member-id] 
  (request :delete (path card-id :membersVoted member-id)))

(defn update-checkitem-on-checklist-on-card [card-id checklist-id check-item-id & [opts]] 
  (request :put (path card-id :checklist checklist-id :checkItem check-item-id ) opts))

(defn delete-checklist-on-card [card-id checklist-id] 
  (request :delete (path card-id :checklists checklist-id)))
