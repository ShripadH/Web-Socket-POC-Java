import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

public class NotificationServiceTest {
  
  private NotificationService notificationService;
  
  @Mock
  private WebClient.Builder webClientBuilder;
  
  @Mock
  private WebClient webClient;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    notificationService = new NotificationService(webClientBuilder);
  }
  
  @Test
  public void sendNotificationEmail_success() {
    NotificationEmailRequest request = new NotificationEmailRequest();
    NotificationResponse expectedResponse = new NotificationResponse();
    expectedResponse.setStatus(NotificationStatus.INPROGRESS);
    
    when(webClientBuilder.build()).thenReturn(webClient);
    when(webClient.post()).thenReturn(webClientRequest);
    when(webClientRequest.uri(any(String.class))).thenReturn(webClientRequest);
    when(webClientRequest.contentType(any(MediaType.class))).thenReturn(webClientRequest);
    when(webClientRequest.body(any(BodyInserters.class))).thenReturn(webClientRequest);
    when(webClientRequest.exchangeToMono(any(Function.class))).thenReturn(Mono.just("success"));
    
    NotificationResponse actualResponse = notificationService.sendNotificationEmail(request);
    
    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
  }
  
  @Test
  public void sendNotificationEmail_failure() {
    NotificationEmailRequest request = new NotificationEmailRequest();
    NotificationResponse expectedResponse = new NotificationResponse();
    expectedResponse.setStatus(NotificationStatus.FAILURE);
    ErrorEntity errorEntity = new ErrorEntity();
    errorEntity.setMessage("Failed to send notification email");
    expectedResponse.setErrors(errorEntity);
    
    when(webClientBuilder.build()).thenReturn(webClient);
    when(webClient.post()).thenReturn(webClientRequest);
    when(webClientRequest.uri(any(String.class))).thenReturn(webClientRequest);
    when(webClientRequest.contentType(any(MediaType.class))).thenReturn(webClientRequest);
    when(webClientRequest.body(any(BodyInserters.class))).thenReturn(webClientRequest);
    when(webClientRequest.exchangeToMono(any(Function.class))).thenReturn(
      Mono.just(ClientResponse.create(HttpStatus.BAD_REQUEST)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body("{\"message\":\"Failed to send notification email\"}")
        .build()
      )
    );
    
    NotificationResponse actualResponse = notificationService.sendNotificationEmail(request);
    
    assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    assertEquals(expectedResponse.getErrors().getMessage(), actualResponse.getErrors().getMessage());
  }
}
