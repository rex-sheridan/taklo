# taklo

Trello => Taco : Taklo <= Clojure

Taklo is a purely functional Clojure library for building requests to the Trello REST API. The requests are in the format used by `clj-http` so just as long as you use a similar HTTP client implementation you can plug it in easily. The library provides a user the ability to customize the stubbed out request processing flow with their own logic. This provides the user flexibility in how they want to use the library.

The library uses almost no dependencies out of the box. Instead, it's designed for extension. There is a default implementation that uses `clj-http` and `org.clojure/data.json` located at [taklo-defaults](https://github.com/rex-sheridan/taklo-defaults). It's designed to show you how you can customize the implementation but is also useful on its own.

Many of the functions in this library have additional optional arguments. You can refer to the Trello API documentation for the list of available optional arguments for a given API method. If the function supports optional arguments it expects them as a map. Some functions do not support optional arguments.

Almost all functions in the library return a map which can be fed into an `clj-http` client. Here's an example:

```clojure
{:headers
    {"Authorization"
    "OAuth oauth_consumer_key=\"api-key\", oauth_token=\"api-token\""},
    :accept :json,
    :debug false,
    :debug-body true,
    :method :get,
    :url "https://api.trello.com/1/boards/board-id",
    :query-params {:foo "bar"}}
```

See [Trello's REST API Introduction](https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/) for more details.

## TODOs

- Add command line usage info 
- Show how to initialize the library
- Show how to replace dependency libraries
- Show how to make it async if someone wants
- Show how to replace the request function
- Add client authorization flows support https://developer.atlassian.com/cloud/trello/guides/rest-api/authorization/#authorizing-a-client
- Decide test strategy for this library
  - Use property based testing 
  - Paths are built correctly
  - Init overrides are respected
  - Library requires no dependencies
  - clj-http compatible requests
  - Default implementations use identity functions
  - Default implementations only build request maps
  - Requests always contain api key and api token
  - Request methods are one of (:get :post :put :delete)
  - Request methods are always populated
  - Endpoint URL is always populated
  - Some requests will contain body but most won't
  - GET and DELETE requests never contain body
  - Paths never contain `:`
  - API function invocations will call custom http-request overrides
  - API function invocations that require a JSON body will attempt to serialize the body as JSON and expect a seq
- Use babashka to make it command-line scriptable
- Use graalvm-native to make command-line binary
- Add simple text ui
- Add a way to easily get the API documentation for a call
- Deploy to clojars
  
## Usage

To use this library in your application you should call `rex-sheridan.taklo.common/init!` and provide your Trello API credentials, HTTP handling functions, and request/response handling functions. See [taklo-defaults](https://github.com/rex-sheridan/taklo-defaults/blob/main/src/rex_sheridan/taklo_defaults.clj#L39) for an example.

## Development

Run the project's tests:

    $ clojure -T:build test

Run the project's CI pipeline and build a JAR:

    $ clojure -T:build ci

This will produce an updated `pom.xml` file with synchronized dependencies inside the `META-INF`
directory inside `target/classes` and the JAR in `target`. You can update the version (and SCM tag)
information in generated `pom.xml` by updating `build.clj`.

Install it locally (requires the `ci` task be run first):

    $ clojure -T:build install

Deploy it to Clojars -- needs `CLOJARS_USERNAME` and `CLOJARS_PASSWORD` environment
variables (requires the `ci` task be run first):

    $ clojure -T:build deploy

Your library will be deployed to net.clojars.rex-sheridan/taklo on clojars.org by default.

## API Implemented

### Actions

- Get an Action
- Update an Action
- Delete an Action
- Get a specific field on an Action
- Get the Board for an Action
- Get the Card for an Action
- Get the List for an Action
- Get the Member of an Action
- Get the Member Creator of an Action
- Get the Organization of an Action
- Update a Comment Action
- Get Action's Reactions
- Create Reaction for Action
- Get Action's Reaction
- Delete Action's Reaction
- List Action's summary of Reactions

### Applications

- Get Application's compliance data

### Batch

- Batch Requests

### Boards

- Get Memberships of a Board
- Get a Board
- Update a Board
- Delete a Board
- Get a field on a Board
- Get Actions of a Board
- Get a Card on a Board
- Get boardStars on a Board
- Get Checklists on a Board
- Get Cards on a Board
- Get filtered Cards on a Board
- Get Custom Fields for Board
- Get Labels on a Board
- Create a Label on a Board
- Get Lists on a Board
- Create a List on a Board
- Get filtered Lists on a Board
- Get the Members of a Board
- Invite Member to Board via email
- Add a Member to a Board
- Remove Member from Board
- Update Membership of Member on a Board
- Update emailPosition Pref on a Board
- Update idEmailList Pref on a Board
- Update showListGuide Pref on a Board
- Update showSidebar Pref on a Board
- Update showSidebarActivity Pref on a Board
- Update showSidebarBoardActions Pref on a Board
- Update showSidebarMembers Pref on a Board
- Create a Board
- Create a calendarKey for a Board
- Create a emailKey for a Board
- Create a Tag for a Board
- Mark Board as viewed
- Get Enabled Power-Ups on Board
- Enable a Power-Up on a Board
- Disable a Power-Up on a Board
- Get Power-Ups on a Board

### Cards

- Create a new Card
- Get a Card
- Update a Card
- Delete a Card
- Get a field on a Card
- Get Actions on a Card
- Get Attachments on a Card
- Create Attachment On Card
- Get an Attachment on a Card
- Delete an Attachment on a Card
- Get the Board the Card is on
- Get checkItems on a Card
- Get Checklists on a Card
- Create Checklist on a Card
- Get checkItem on a Card
- Update a checkItem on a Card
- Delete checkItem on a Card
- Get the List of a Card
- Get the Members of a Card
- Get Members who have voted on a Card
- Add Member vote to Card
- Get pluginData on a Card
- Get Stickers on a Card
- Add a Sticker to a Card
- Get a Sticker on a Card
- Update a Sticker on a Card
- Delete a Sticker on a Card
- Update Comment Action on a Card
- Delete a comment on a Card
- Update Custom Field item on Card
- Get Custom Field Items for a Card
- Add a new comment to a Card
- Add a Label to a Card
- Add a Member to a Card
- Create a new Label on a Card
- Mark a Card's Notifications as read
- Remove a Label from a Card
- Remove a Member from a Card
- Remove a Member's Vote on a Card
- Update Checkitem on Checklist on Card
- Delete a Checklist on a Card

### Checklists

- Create a Checklist
- Get a Checklist
- Update a Checklist
- Delete a Checklist
- Get field on a Checklist
- Update field on a Checklist
- Get the Board the Checklist is on
- Get the Card a Checklist is on
- Get Checkitems on a Checklist
- Create Checkitem on Checklist
- Get a Checkitem on a Checklist
- Delete Checkitem from Checklist

## CustomFields

- Create a new Custom Field on a Board
- Get a Custom Field
- Update a Custom Field definition
- Delete a Custom Field definition
- Get Options of Custom Field drop down
- Add Option to Custom Field dropdown
- Get Option of Custom Field dropdown
- Delete Option of Custom Field dropdown

### Emoji

- List available Emoji

### Enterprises

- Get an Enterprise
- Get auditlog data for an Enterprise
- Get Enterprise admin Members
- Get signupUrl for Enterprise
- Get Members of Enterprise
- Get a Member of Enterprise
- Get whether an organization can be transferred to an enterprise.
- Get a bulk list of organizations that can be transferred to an enterprise.
- Decline enterpriseJoinRequests from one organization or a bulk list of organizations.
- Get ClaimableOrganizations of an Enterprise
- Get PendingOrganizations of an Enterprise
- Create an auth Token for an Enterprise.
- Transfer an Organization to an Enterprise.
- Update a Member's licensed status
- Deactivate a Member of an Enterprise.
- Update Member to be admin of Enterprise
- Remove a Member as admin from Enterprise.
- Delete an Organization from an Enterprise.
- Bulk accept a set of organizations to an Enterprise.

### Labels

- Get a Label
- Update a Label
- Delete a Label
- Update a field on a label
- Create a Label

### Lists

- Get a List
- Update a List
- Create a new List
- Archive all Cards in List
- Move all Cards in List
- Archive or unarchive a list
- Move List to Board
- Update a field on a List
- Get Actions for a List
- Get the Board a List is on
- Get Cards in a List

### Members

- Get a Member
- Update a Member
- Get a field on a Member
- Get a Member's Actions
- Get Member's custom Board backgrounds
- Upload new boardBackground for Member
- Get a boardBackground of a Member
- Update a Member's custom Board background
- Delete a Member's custom Board background
- Get a Member's boardStars
- Create Star for Board
- Get a boardStar of Member
- Update the position of a boardStar of Member
- Delete Star for Board
- Get Boards that Member belongs to
- Get Boards the Member has been invited to
- Get Cards the Member is on
- Get a Member's custom Board Backgrounds
- Create a new custom Board Background
- Get custom Board Background of Member
- Update custom Board Background of Member
- Delete custom Board Background of Member
- Get a Member's customEmojis
- Create custom Emoji for Member
- Get a Member's custom Emoji
- Get Member's custom Stickers
- Create custom Sticker for Member
- Get a Member's custom Sticker
- Delete a Member's custom Sticker
- Get Member's Notifications
- Get Member's Organizations
- Get Organizations a Member has been invited to
- Get Member's saved searched
- Create saved Search for Member
- Get a saved search
- Update a saved search
- Delete a saved search
- Get Member's Tokens
- Create Avatar for Member
- Dismiss a message for Member

### Notifications

- Get a Notification
- Update a Notification's read status
- Get a field of a Notification
- Mark all Notifications as read
- Update Notification's read status
- Get the Board a Notification is on
- Get the Card a Notification is on
- Get the List a Notification is on
- Get the Member a Notification is about (not the creator)
- Get the Member who created the Notification
- Get a Notification's associated Organization

### Organizations

- Create a new Organization
- Get an Organization
- Update an Organization
- Delete an Organization
- Get field on Organization
- Get Actions for Organization
- Get Boards in an Organization
- Retrieve Organization's Exports
- Create Export for Organizations
- Get the Members of an Organization
- Update an Organization's Members
- Get Memberships of an Organization
- Get a Membership of an Organization
- Get the pluginData Scoped to Organization
- Get Tags of an Organization
- Create a Tag in Organization
- Update a Member of an Organization
- Remove a Member from an Organization
- Deactivate or reactivate a member of an Organization
- Update logo for an Organization
- Delete Logo for Organization
- Remove a Member from an Organization and all Organization Boards
- Remove the associated Google Apps domain from a Workspace
- Delete the email domain restriction on who can be invited to the Workspace
- Delete an Organization's Tag
- Get Organizations new billable guests

### Plugins

- Get a Plugin
- Update a Plugin
- Create a Listing for Plugin
- Get Plugin's Member privacy compliance
- Updating Plugin's Listing

### Search

- Search Trello
- Search for Members

### Tokens

- Get a Token
- Get Token's Member
- Get Webhooks for Token
- Create Webhooks for Token
- Get a Webhook belonging to a Token
- Update a Webhook created by Token
- Delete a Webhook created by Token
- Delete a Token

### Webhooks

- Create a Webhook
- Get a Webhook
- Update a Webhook
- Delete a Webhook
- Get a field on a Webhook

## License

Copyright © 2022 Rex Sheridan

_EPLv1.0 is just the default for projects generated by `clj-new`: you are not_
_required to open source this project, nor are you required to use EPLv1.0!_
_Feel free to remove or change the `LICENSE` file and remove or update this_
_section of the `README.md` file!_

Distributed under the Eclipse Public License version 1.0.
