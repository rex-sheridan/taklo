(ns rex-sheridan.taklo.members
    (:require
     [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :members))

(defn get-my-boards
  "Get boards for the current user"
  [& [opts]]
  (request :get "members/me/boards" opts))

(defn get-members-orgs []
  (request :get "members/me/organizations"))

(defn get-member [member-id & [opts]]
  (request :get (path member-id) opts))

(defn update-member [member-id & [opts]]
  (request :put (path member-id) opts))

(defn get-field-on-member [member-id field ]
  (request :get (path member-id field)))

(defn get-members-actions [member-id & [opts]]
  (request :get (path member-id :actions) opts))

(defn get-members-custom-board-backgrounds [member-id & [opts]]
  (request :get (path member-id :boardBackgrounds) opts))

(defn upload-new-board-background-for-member [member-id file]
  (request :post (path member-id :boardBackgrounds) {:file file}))

(defn get-board-background-of-member [member-id background-id & [opts]]
  (request :get (path member-id :boardBackgrounds background-id) opts))

(defn update-members-custom-board-background [member-id background-id & [opts]]
  (request :put (path member-id :boardBackgrounds background-id) opts))

(defn delete-members-custom-board-background [member-id background-id]
  (request :delete (path member-id :boardBackgrounds background-id)))

(defn get-members-board-stars [member-id]
  (request :get (path member-id :boardStars)))

(defn create-star-for-board [member-id board-id pos]
  (request :post (path member-id :boardStars) {:boardId board-id :pos pos}))

(defn get-board-star-of-member [member-id star-id]
  (request :get (path member-id :boardStars star-id)))

(defn update-position-of-board-star-of-member [member-id star-id & [opts]]
  (request :put (path member-id :boardStars star-id) opts))

(defn delete-star-for-board [member-id star-id]
  (request :delete (path member-id :boardStars star-id)))

(defn get-boards-that-member-belongs-to [member-id & [opts]]
  (request :get (path member-id :boards) opts))

(defn get-boards-member-has-been-invited-to [member-id & [opts]]
  (request :get (path member-id :boardsInvited) opts))

(defn get-cards-member-is-on [member-id & [opts]]
  (request :get (path member-id :cards) opts))

(defn get-members-custom-board-backgrounds [member-id]
  (request :get (path member-id :customBoardBackgrounds)))

(defn create-new-custom-board-background [member-id file]
  (request :post (path member-id :customBoardBackgrounds) {:file file}))

(defn get-custom-board-background-of-member [member-id background-id]
  (request :get (path member-id :customBoardBackgrounds background-id)))

(defn update-custom-board-background-of-member [member-id background-id & [opts]]
  (request :put (path member-id :customBoardBackgrounds background-id) opts))

(defn delete-custom-board-background-of-member [member-id background-id]
  (request :delete (path member-id :customBoardBackgrounds background-id)))

(defn get-members-custom-emojis [member-id]
  (request :get (path member-id :customEmoji)))

(defn create-custom-emoji-for-member [member-id file name]
  (request :post (path member-id :customEmoji) {:file file :name name}))

(defn get-members-custom-emoji [member-id emoji-id & [opts]]
  (request :get (path member-id :customEmoji emoji-id) opts))

(defn get-members-custom-stickers [member-id]
  (request :get (path member-id :customStickers)))

(defn create-custom-sticker-for-member [member-id file]
  (request :post (path member-id :customStickers) {:file file}))

(defn get-members-custom-sticker [member-id sticker-id & [opts]]
  (request :get (path member-id :customStickers sticker-id) opts))

(defn delete-members-custom-sticker [member-id sticker-id]
  (request :delete (path member-id :customStickers sticker-id)))

(defn get-members-notifications [member-id & [opts]]
  (request :get (path member-id :notifications) opts))

(defn get-members-organizations [member-id & [opts]]
  (request :get (path member-id :organizations) opts))

(defn get-organizations-member-has-been-invited-to [member-id & [opts]]
  (request :get (path member-id :organizationsInvited) opts))

(defn get-members-saved-searched [member-id]
  (request :get (path member-id :savedSearches)))

(defn create-saved-search-for-member [member-id name query pos]
  (request :post (path member-id :savedSearches)
           {:name name :query query :pos pos}))

(defn get-saved-search [member-id search-id]
  (request :get (path member-id :savedSearches search-id)))

(defn update-saved-search [member-id search-id & [opts]]
  (request :put (path member-id :savedSearches search-id) opts))

(defn delete-saved-search [member-id search-id]
  (request :delete (path member-id :savedSearches search-id)))

(defn get-members-tokens [member-id & [opts]]
  (request :get (path member-id :tokens) opts))

(defn create-avatar-for-member [member-id file]
  (request :post (path member-id :avatar) {:file file}))

(defn dismiss-message-for-member [member-id value]
  (request :post (path member-id :oneTimeMessagesDismissed) {:value value}))

