(ns rex-sheridan.taklo.enterprises
  "https://developer.atlassian.com/cloud/trello/rest/api-group-enterprises/#api-group-enterprises"
  (:require
   [rex-sheridan.taklo.common :refer [request with-path-prefix]]))

(def ^:private path (partial with-path-prefix :enterprises))

(defn get-enterprise [enterprise-id & [opts]]
  (request :get (path enterprise-id) opts))

(defn get-auditlog-data-for-enterprise [enterprise-id]
  (request :get (path enterprise-id :auditlog)))

(defn get-enterprise-admin-members [enterprise-id & [opts]]
  (request :get (path enterprise-id :admins) opts))

(defn get-signup-url-for-enterprise [enterprise-id & [opts]]
  (request :get (path enterprise-id :signupUrl) opts))

(defn get-members-of-enterprise [enterprise-id & [opts]]
  (request :get (path enterprise-id :members) opts))

(defn get-member-of-enterprise [enterprise-id member-id & [opts]]
  (request :get (path enterprise-id :members member-id) opts))

(defn get-organization-is-transferable-to-enterprise [enterprise-id organization-id]
  (request :get (path enterprise-id :transferrable :organization organization-id)))

(defn get-organizations-transferable-to-enterprise
  "Get a list of organizations that can be transferred to an enterprise when given a 
   bulk list of organizations."
  [enterprise-id organization-ids]
  (request :get (path enterprise-id :transferrable :bulk organization-ids)))

(defn decline-enterprise-join-requests 
  "Decline enterpriseJoinRequests from one organization or bulk amount of organizations"
  [enterprise-id organization-ids]
  (request :put (path enterprise-id :enterpriseJoinRequest :bulk organization-ids)))

(defn get-claimable-organizations-of-enterprise [enterprise-id & [opts]]
  (request :get (path enterprise-id :claimableOrganizations) opts))

(defn get-pending-organizations-of-enterprise [enterprise-id & [opts]]
  (request :get (path enterprise-id :pendingOrganizations) opts))

(defn create-auth-token-for-enterprise [enterprise-id & [opts]]
  (request :post (path enterprise-id :tokens) opts))

(defn transfer-organization-to-enterprise [enterprise-id organization-id]
  (request :put (path enterprise-id :organizations organization-id)))

(defn update-members-licensed-status [enterprise-id member-id value]
  (request :put (path enterprise-id :members member-id :licensed) {:value value}))

(defn deactivate-member-of-enterprise [enterprise-id member-id value & [opts]]
  (request :put (path enterprise-id :members member-id :deactivated) 
           (merge {:value value} opts)))

(defn update-member-to-be-admin-of-enterprise [enterprise-id member-id]
  (request :put (path enterprise-id :admins member-id)))

(defn remove-member-as-admin-from-enterprise [enterprise-id member-id]
  (request :delete (path enterprise-id :admins member-id)))

(defn delete-organization-from-enterprise [enterprise-id organization-id]
  (request :delete (path enterprise-id :organizations organization-id)))

(defn bulk-accept-set-of-organizations-to-enterprise [enterprise-id organization-ids & [opts]]
  (request :get (path enterprise-id :organizations :bulk organization-ids) opts))
