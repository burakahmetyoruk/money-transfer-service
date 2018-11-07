package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Transfer {

    private Long id;
    private BigDecimal transferAmount;
    private Account transferrer;
    private Account transferred;
}
