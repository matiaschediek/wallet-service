package mchediek.wallet_service.bdd.stepdefs;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestContext {
    private final Map<String, String> userWalletMap = new HashMap<>();
    private final Map<String, Double> lastQueriedBalances = new HashMap<>();

    public void clear() {
        userWalletMap.clear();
        lastQueriedBalances.clear();
    }

    public void setWalletIdForUser(String userId, String walletId) {
        userWalletMap.put(userId, walletId);
    }

    public String getWalletIdForUser(String userId) {
        return userWalletMap.get(userId);
    }

    public void setLastQueriedBalance(String userId, Double balance) {
        lastQueriedBalances.put(userId, balance);
    }

    public Double getLastQueriedBalance(String userId) {
        return lastQueriedBalances.getOrDefault(userId,  -1d);
    }
}