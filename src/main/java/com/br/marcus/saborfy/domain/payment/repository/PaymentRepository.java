package com.br.marcus.saborfy.domain.payment.repository;

import com.br.marcus.saborfy.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
        SELECT COALESCE(SUM(p.amount), 0)
        FROM Payment p
        WHERE p.status = 'APPROVED'
          AND p.paidAt BETWEEN :start AND :end
    """)
    BigDecimal getRevenueBetween(
            @Param("start") Instant start,
            @Param("end") Instant end
    );

    @Query("""
    SELECT COALESCE(SUM(p.amount), 0)
    FROM Payment p
    WHERE p.status = 'APPROVED'
      AND p.paidAt >= :start
""")
    BigDecimal getTotalLast30Days(@Param("start") Instant start);

    @Query(value = """
        SELECT DATE(p.paid_at) AS day,
               COUNT(*) AS total
        FROM payment p
        WHERE p.status = 'APPROVED'
          AND p.paid_at >= NOW() - INTERVAL 7 DAY
        GROUP BY DATE(p.paid_at)
        ORDER BY day
    """, nativeQuery = true)
    List<Object[]> getSalesLast7Days();

    List<Payment> findTop10ByOrderByPaidAtDesc();
}
