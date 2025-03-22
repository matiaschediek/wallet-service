Feature: Wallet Management

  Scenario: Create a new wallet
    Given a user "d545b126-f714-469f-b59a-5487e86fe871" does not yet have a wallet
    When the user "d545b126-f714-469f-b59a-5487e86fe871" requests a new wallet
    Then a wallet is successfully created for user "d545b126-f714-469f-b59a-5487e86fe871"
    And the user "d545b126-f714-469f-b59a-5487e86fe871" wallet balance should be 0

  Scenario: Create a wallet for a user that does have one
    Given a user "CEE873BC-993D-4492-A7CC-A1D12E584E19" has an existing wallet with a balance of 0
    When the user "CEE873BC-993D-4492-A7CC-A1D12E584E19" requests a new wallet
    Then the system should report an error message "User already has a wallet"

  Scenario: Check current balance
    Given a user "BD19E48D-7720-4638-B6B5-2261CBE2C9AA" has an existing wallet with a balance of 100
    When the user "BD19E48D-7720-4638-B6B5-2261CBE2C9AA" checks the current balance
    Then the system should report a balance of 100 for user "BD19E48D-7720-4638-B6B5-2261CBE2C9AA"

  Scenario: Check historical balance
    Given a user "2C2E15A2-72F6-461B-9169-CF121882764E" has an existing wallet with a balance of 100
    And the user "2C2E15A2-72F6-461B-9169-CF121882764E" wallet's balance was 50 at "2025-03-10 10:00:00"
    When the user "2C2E15A2-72F6-461B-9169-CF121882764E" checks the balance at "2025-03-10 10:00:00"
    Then the system should report a balance of 50 for user "2C2E15A2-72F6-461B-9169-CF121882764E"

  Scenario: Deposit money into a wallet
    Given a user "C49750F7-5CC9-4D61-B1EE-2636614CF779" has an existing wallet with a balance of 100
    When the user "C49750F7-5CC9-4D61-B1EE-2636614CF779" deposits 50
    Then the user "C49750F7-5CC9-4D61-B1EE-2636614CF779" wallet balance should be 150

  Scenario: Withdraw money from a wallet
    Given a user "3CEDC98B-7A7B-424D-965B-7913BDE50759" has an existing wallet with a balance of 150
    When the user "3CEDC98B-7A7B-424D-965B-7913BDE50759" withdraws 70
    Then the user "3CEDC98B-7A7B-424D-965B-7913BDE50759" wallet balance should be 80

  Scenario: Transfer money between wallets
    Given a user "55E33BB9-2035-4121-A318-EDCEB7600E7E" has an existing wallet with a balance of 100
    And a user "E3F4A2B1-C39A-401E-BB6F-FC1786F3A57A" has an existing wallet with a balance of 0
    When user "55E33BB9-2035-4121-A318-EDCEB7600E7E" transfers 30 to user "E3F4A2B1-C39A-401E-BB6F-FC1786F3A57A"
    Then the user "55E33BB9-2035-4121-A318-EDCEB7600E7E" wallet balance should be 70
    And the user "E3F4A2B1-C39A-401E-BB6F-FC1786F3A57A" wallet balance should be 30