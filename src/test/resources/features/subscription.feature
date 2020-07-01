Feature: SubscriptionPlanTest

  Scenario Outline: As a subscription I want to create a new SubscriptionPlan
    And I sending SubscriptionPlan to be created with subscriptionPlan_id <id>,name <name>, description <description>,duration <duration>, price <price>

    Examples:
      | id  | name   | description | duration | price |
      | 1   | Basico | plan Basico | 5        | 29.99 |