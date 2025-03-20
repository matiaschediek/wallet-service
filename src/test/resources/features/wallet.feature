Feature: Wallet Management

  # 1. Create wallets for users
  Scenario: Create a new wallet
    Given a user "D545B126-F714-469F-B59A-5487E86FE871" does not yet have a wallet
    When the user "D545B126-F714-469F-B59A-5487E86FE871" requests a new wallet
    Then a wallet is successfully created for user "D545B126-F714-469F-B59A-5487E86FE871"
    And the user "D545B126-F714-469F-B59A-5487E86FE871" wallet balance should be 0

  # 2. Check the current balance
  Scenario: Check current balance
    Given a user "D545B126-F714-469F-B59A-5487E86FE871" has an existing wallet with a balance of 100
    When the user "D545B126-F714-469F-B59A-5487E86FE871" checks the current balance
    Then the system should report a balance of 100

  # 3. Check the balance at a historical point in time
  Scenario: Check historical balance
    Given a user "D545B126-F714-469F-B59A-5487E86FE871" has an existing wallet with a balance of 100
    And the wallet's balance was 50 at "2025-03-10T10:00:00"
    When the user "D545B126-F714-469F-B59A-5487E86FE871" checks the balance at "2025-03-10T10:00:00"
    Then the system should report a balance of 50

  # 4. Deposit funds
  Scenario: Deposit money into a wallet
    Given a user "D545B126-F714-469F-B59A-5487E86FE871" has an existing wallet with a balance of 100
    When the user "D545B126-F714-469F-B59A-5487E86FE871" deposits 50
    Then the user "D545B126-F714-469F-B59A-5487E86FE871" wallet balance should be 150

  # 5. Withdraw funds
  Scenario: Withdraw money from a wallet
    Given a user "D545B126-F714-469F-B59A-5487E86FE871" has an existing wallet with a balance of 150
    When the user "D545B126-F714-469F-B59A-5487E86FE871" withdraws 70
    Then the user "D545B126-F714-469F-B59A-5487E86FE871" wallet balance should be 80

  # 6. Transfer money between users
  Scenario: Transfer money between wallets
    Given a user "D545B126-F714-469F-B59A-5487E86FE871" has an existing wallet with a balance of 100
    And a user "E3F4A2B1-C39A-401E-BB6F-FC1786F3A57A" has an existing wallet with a balance of 0
    When user "D545B126-F714-469F-B59A-5487E86FE871" transfers 30 to user "E3F4A2B1-C39A-401E-BB6F-FC1786F3A57A"
    Then the user "D545B126-F714-469F-B59A-5487E86FE871" wallet balance should be 70
    And the user "E3F4A2B1-C39A-401E-BB6F-FC1786F3A57A" wallet balance should be 30