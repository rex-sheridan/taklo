(ns rex-sheridan.taklo.organizations
  "https://developer.atlassian.com/cloud/trello/rest/api-group-organizations"
   (:require
    [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :organizations))

(defn create-new-organization [display-name & [opts]]
  (request :post "organizations" (merge {:displayName display-name} opts)))

(defn get-organization [organization-id]
  (request :get (path organization-id)))

(defn update-organization [organization-id & [opts]]
  (request :put (path organization-id) opts))

(defn delete-organization [organization-id]
  (request :delete (path organization-id)))

(defn get-field-on-organization [organization-id field]
  (request :get (path organization-id field)))

(defn get-actions-for-organization [organization-id]
  (request :get (path organization-id :actions)))

(defn get-boards-in-organization [organization-id & [opts]]
  (request :get (path organization-id :boards) opts))

(defn retrieve-organizations-exports [organization-id]
  (request :get (path organization-id :exports)))

(defn create-export-for-organizations [organization-id & [opts]]
  (request :post (path organization-id :exports) opts))

(defn get-members-of-organization [organization-id ]
  (request :get (path organization-id :members)))

(defn update-organizations-members [organization-id email full-name & [opts]]
  (request :put (path organization-id :members) 
           (merge {:email email :fullName full-name} opts)))

(defn get-memberships-of-organization [organization-id & [opts]]
  (request :get (path organization-id :memberships) opts))

(defn get-membership-of-organization [organization-id membership-id & [opts]]
  (request :get (path organization-id :memberships membership-id) opts))

(defn get-plugin-data-scoped-to-organization [organization-id]
  (request :get (path organization-id :pluginData)))

(defn get-tags-of-organization [organization-id]
  (request :get (path organization-id :tags)))

(defn create-tag-in-organization [organization-id & [opts]]
  (request :post (path organization-id :tags) opts))

(defn update-member-of-organization [organization-id member-id type]
  (request :put (path organization-id :members member-id) {:type type}))

(defn remove-member-from-organization [organization-id member-id]
  (request :delete (path organization-id :members member-id)))

(defn deactivate-or-reactivate-member-of-organization [organization-id member-id value]
  (request :put (path organization-id :members member-id :deactivated) {:value value}))

(defn update-logo-for-organization [organization-id file]
  (request :post (path organization-id :logo) {:file file}))

(defn delete-logo-for-organization [organization-id]
  (request :delete (path organization-id :logo)))

(defn remove-member-from-organization-and-all-organization-boards [organization-id member-id]
  (request :delete (path organization-id :members member-id :all)))

(defn remove-associated-google-apps-domain-from-workspace [organization-id]
  (request :delete (path organization-id :prefs :associatedDomain)))

(defn delete-email-domain-restriction-on-who-can-be-invited-to-workspace [organization-id]
  (request :delete (path organization-id :prefs :orgInviteRestrict)))

(defn delete-organizations-tag [organization-id tag-id]
  (request :delete (path organization-id :tags tag-id)))

(defn get-organizations-new-billable-guests [organization-id board-id]
  (request :get (path organization-id :newBillableGuests board-id)))

