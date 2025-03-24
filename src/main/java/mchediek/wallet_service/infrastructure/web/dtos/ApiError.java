package mchediek.wallet_service.infrastructure.web.dtos;

import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standard API error response")
public class ApiError {

    @Schema(description = "Código HTTP", example = "422")
    private int status;

    @Schema(description = "Short description ", example = "Unprocessable Entity")
    private String error;

    @Schema(description = "Detailed message", example = "The amount is invalid")
    private String message;

    @Schema(description = "Path of the request", example = "/wallets")
    private String path;
    @Schema(description = "Timestamp of the error", example = "2021-09-01T12:00:00")
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, String path) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}