package com.fourkbsys.eventms.data;

import com.fourkbsys.eventms.commons.DateStringValidator;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface EventEntityCriteriaFilter {
    public static Specification<EventEntity> filterEvent(String value) {

        return (root, query, builder) -> {
            return switch (value) {
                case "tomorrow" -> builder.between(root.get("fromDate"),
                        LocalDate.now().plusDays(1).atStartOfDay(),
                        LocalDate.now().plusDays(1).atTime(23, 59));
                case "today" -> builder.between(root.get("fromDate"),
                        LocalDate.now().atStartOfDay(),
                        LocalDate.now().atTime(23, 59));
                case "past" -> builder.lessThan(root.get("fromDate"),
                        LocalDate.now().atStartOfDay());
                case "" -> builder.greaterThanOrEqualTo(root.get("fromDate"),
                        LocalDate.now().atStartOfDay());
                default ->
                        !DateStringValidator.isValid(value) ? null :
                            builder.between(root.get("fromDate"),
                                    LocalDate.parse(value, DateTimeFormatter.BASIC_ISO_DATE).atStartOfDay(),
                                    LocalDate.parse(value, DateTimeFormatter.BASIC_ISO_DATE).atTime(23, 59));
            };
        };

    }

}
