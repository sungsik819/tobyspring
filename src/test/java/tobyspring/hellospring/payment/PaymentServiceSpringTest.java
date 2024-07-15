package tobyspring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.ObjectFactory;
import tobyspring.hellospring.TestObjectFactory;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceSpringTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(TestObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
    }
}