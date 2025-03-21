package mchediek.wallet_service.infrastructure.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLogger {
    private static final Logger auditLogger = LoggerFactory.getLogger("auditLogger");

    public static void log(String message) {
        auditLogger.info(message);
    }
}