(ns rex-sheridan.taklo.boards
    (:require [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :boards))

(defn get-board-memberships [board-id & [opts]]
  (request :get (path board-id :memberships) opts))

(defn get-board [board-id & [opts]]
  (request :get (path board-id) opts))

(defn update-board [board-id board-content]
  (request :put (path board-id) board-content))

(defn delete-board [board-id]
  (request :delete (path board-id)))

(def board-fields [:closed :dateLastActivity :dateLastView :desc :descData
                   :idMemberCreator :idOrganization :invitations :invited
                   :labelNames :memberships :name :pinned :powerUps :prefs
                   :shortLink :shortUrl :starred :subscribed :url])

(defn get-board-field [board-id field]
  (request :get (path board-id field)))

(defn get-board-actions [board-id & [opts]]
  (request :get (path board-id :actions) opts))

(defn get-board-card [board-id card-id]
  (request :get (path board-id :cards card-id)))

(defn get-board-stars [board-id & [opts]]
  (request :get (path board-id :boardStars) opts))

(defn get-board-checklists [board-id]
  (request :get (path board-id :checklists)))

(defn get-board-cards [board-id]
  (request :get (path board-id :cards)))

(defn get-board-cards-filtered
  "Valid values for filter: all, closed, none, open, visible."
  [board-id filter]
  (request :get (path board-id :cards filter)))

(defn get-board-custom-fields [board-id]
  (request :get (path board-id :customFields)))

(defn get-board-labels [board-id]
  (request :get (path board-id :labels)))

(defn create-board-label [board-id name color]
  (request :post (path board-id :labels)
           {:name name
            :color color}))

(defn get-lists-by-board
  "Get lists by `board-id`"
  ([board-id] (get-lists-by-board board-id {}))
  ([board-id opts]
   (request :get (path board-id :lists) opts)))

(defn create-list-on-board [board-id name & [opts]]
  (request :post (path board-id :lists) (merge {:name name} opts)))

(defn get-filtered-lists-on-board [board-id filter]
  (request :get (path board-id :lists filter)))

(defn get-board-members [board-id]
  (request :get (path board-id :members)))

(defn invite-member-to-board-by-email [board-id email type full-name]
  (request :put (path board-id :members)
           {:fullName full-name}
           {:email email
            :type type}))

(defn add-member-to-board [board-id member-id type
                           & {:keys [allow-billable-guest]
                              :or {allow-billable-guest false}}]
  (request :put (path board-id :members member-id)
           {:type type
            :allowBillableGuest allow-billable-guest}))

(defn remove-member-from-board [board-id member-id]
  (request :delete (path board-id :members member-id) {}))

(defn update-membership-of-member-on-board [board-id membership-id type
                                            & {:keys [member-fields]
                                               :or {member-fields "fullName,username"}}]
  (request :put (path board-id :memberships membership-id)
           {:type type 
            :member_fields member-fields}))

(defn get-board-prefs [board-id]
  (request :get (path board-id :myPrefs)))

(defn update-email-position-pref-for-board [board-id position]
  (request :put (path board-id :myPrefs :emailPosition)
           {:value position}))

(defn update-email-list-pref-for-board [board-id list-id]
  (request :put (path board-id :myPrefs :idEmailList)
           {:value list-id}))

(defn update-show-list-guide-pref-for-board [board-id value]
  (request :put (path board-id :myPrefs :showListGuide)
           {:value value}))

(defn update-show-sidebar-pref-for-board [board-id value]
  (request :put (path board-id :myPrefs :showSidebar)
           {:value value}))

(defn update-show-sidebar-activity-pref-for-board [board-id value]
  (request :put (path board-id :myPrefs :showSidebarActivity)
           {:value value}))

(defn update-show-sidebar-actions-pref-for-board [board-id value]
  (request :put (path board-id :myPrefs :showSidebarBoardActions)
           {:value value}))

(defn update-show-sidebar-members-pref-for-board [board-id value]
  (request :put (path board-id :myPrefs :showSidebarMembers)
           {:value value}))

(defn create-board [name & [opts]]
  (request :post :boards (merge {:name name} opts)))

(defn create-calendar-key-for-board [board-id]
  (request :post (path board-id :calendarKey :generate)))

(defn create-email-key-for-board [board-id]
  (request :post (path board-id :emailKey :generate)))

(defn create-tag-for-board [board-id tag-id]
  (request :post (path board-id :idTags) {:value tag-id}))

(defn mark-board-as-viewed [board-id]
  (request :post (path board-id :markedAsViewed)))

(defn get-enabled-powerups-for-board [board-id]
  (request :get (path board-id :boardPlugins)))

(defn get-powerups-for-board [board-id]
  (request :get (path board-id :plugins)))