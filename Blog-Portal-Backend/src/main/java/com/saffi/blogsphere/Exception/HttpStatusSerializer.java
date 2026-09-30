package com.saffi.blogsphere.Exception;

import org.springframework.http.HttpStatus;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;

/**
 * Serializes HTTP statuses using the legacy API enum-name representation.
 */
public final class HttpStatusSerializer extends tools.jackson.databind.ValueSerializer<HttpStatus> {
    @Override
    public void serialize(final HttpStatus status, final JsonGenerator generator,
            final tools.jackson.databind.SerializationContext context)
            throws JacksonException {
        generator.writeString(status.name());
    }
}
