package com.assu.study.chap05.controller;

import com.assu.study.chap05.domain.FileDownloadException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

@RestController
public class ReservationController {
  @GetMapping("/hotels/{hotelId}/rooms/{roomNumber}/reservations")
  public List<Long> getReservationsByPaging(
          @PathVariable Long hotelId,
          @PathVariable String roomNumber,
          Pageable pageable // @RequestParam 이 없어도 Pageable 클래스를 인자로 선언하면 page, size, sort 파라메터값을 매핑한 Pageagle 객체를 주입함
  ) {
    System.out.println("page: " + pageable.getPageNumber());  // page 파라메터값 리턴
    System.out.println("size: " + pageable.getPageSize());  // size 파라메터값 리턴

    // Pageable 의 getSort() 는 sort 파라메터값과 대응하는 Sort 객체 리턴하고, sort 파라메터는 하나 이상의 값일 수 있음
    // 따라서 Sort 객체는 inner class 인 Sort.order 객체스트림을 구현함
    // stream() 을 사용하면 클라이언트가 전달한 파라메터 집합 처리 가능
    pageable.getSort().stream().forEach(order -> {
      System.out.println("sort: " + order.getProperty() + ", " + order.getDirection());
    });

    return Collections.emptyList();
  }

  // pdf byte 정보 조회
  @GetMapping(value = "/hotels/pdf/getByte")
  public ResponseEntity<byte[]> getInvoice() {
    String filePath = "pdf/hotel_invoice.pdf";  // resources/pdf/hotel_invoice.pdf

    try (InputStream inputStream = new ClassPathResource(filePath).getInputStream()) {
      // inputStream 객체를 byte[] 로 변환 후 리턴
      byte[] bytes = StreamUtils.copyToByteArray(inputStream);
      return new ResponseEntity<>(bytes, HttpStatus.OK);
    } catch (Throwable th) {
      th.printStackTrace();
      throw new FileDownloadException("file read Error~");
    }
  }

  // pdf download
  @GetMapping(value = "/hotels/pdf/download", produces = "application/pdf")
  public void downloadInvoice(
          HttpServletResponse response
  ) {
    String filePath = "pdf/hotel_invoice.pdf";  // resources/pdf/hotel_invoice.pdf

    try (InputStream inputStream = new ClassPathResource(filePath).getInputStream()) {
      OutputStream outputStream = response.getOutputStream();

      // HttpServletResponse 객체의 메서드를 사용하여 HTTP status code, header 설정
      response.setStatus(HttpStatus.OK.value());
      response.setContentType(MediaType.APPLICATION_PDF_VALUE);
      response.setHeader("Content-Disposition", "filename=invoice.pdf");

      // 파일을 읽어오는 InputStream 객체에서 데이터를 읽고, HttpServletResponse 의 OutputStream 객체에 데이터를 씀
      StreamUtils.copy(inputStream, outputStream);
    } catch (Throwable th) {
      th.printStackTrace();
      throw new FileDownloadException("file download error~");
    }
  }
}
