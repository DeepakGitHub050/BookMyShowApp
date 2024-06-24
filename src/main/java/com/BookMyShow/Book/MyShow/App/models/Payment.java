package com.BookMyShow.Book.MyShow.App.models;

import com.BookMyShow.Book.MyShow.App.models.Enums.PaymentGateWayProvider;
import com.BookMyShow.Book.MyShow.App.models.Enums.PaymentMode;
import com.BookMyShow.Book.MyShow.App.models.Enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private String refNo;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;
    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private PaymentGateWayProvider paymentGateWayProvider;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;
}
