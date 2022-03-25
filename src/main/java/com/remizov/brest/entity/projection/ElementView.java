package com.remizov.brest.entity.projection;

import com.remizov.brest.entity.Element;
import com.remizov.brest.entity.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Simple ElementView projection with hidden id field,provide target value.
 *
 * @author Alex Remizov
 */
//@Projection(name = "elementView",types = {Element.class})
//public interface ElementView {
//
//    @Value("#{target.id}")
//    Integer getId();
//
//    String getName();
//    String getDescription();
//    String getValue();
//}
