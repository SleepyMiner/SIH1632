package org.au.careercove.api.data.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode()
public class ValueType {
    
    private String id;
    private String label;
    private String description;

    public ValueType(String id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }
}
