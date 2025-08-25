package dev.mhzars.projects.commons.resumeapidockercompose.podam;

import java.lang.annotation.Annotation;
import java.util.List;
import uk.co.jemos.podam.common.AttributeStrategy;

public class GenerateUniqueIdStrategy implements AttributeStrategy<String> {
    private final AttributeStrategy<String> uuidStrategy = new GenerateUUIDStrategy();
    private final AttributeStrategy<String> objectIdStrategy = new GenerateObjectIdStrategy();

    /**
     * Generates a unique identifier value based on the configured database type (MongoDB or
     * PostgreSQL).
     *
     * <p>The database type is determined by the system property {@code db.mongo}:
     *
     * <ul>
     *   <li>If {@code db.mongo=true} (default), generates a MongoDB {@link
     *       org.bson.types.ObjectId}.
     *   <li>If {@code db.mongo=false}, generates a PostgreSQL-compatible {@link java.util.UUID}.
     * </ul>
     *
     * @param attrType The type of the attribute for which the value is generated (e.g., {@code
     *     String.class}).
     * @param attrAnnotations A list of annotations present on the attribute (unused in this
     *     implementation).
     * @return A unique identifier as a {@code String}:
     *     <ul>
     *       <li>MongoDB: 24-character hexadecimal {@code ObjectId} (e.g.,
     *           "507f191e810c19729de860ea").
     *       <li>PostgreSQL: 36-character {@code UUID} (e.g.,
     *           "123e4567-e89b-12d3-a456-426614174000").
     *     </ul>
     *
     * @throws IllegalArgumentException If the attribute type is unsupported by the underlying
     *     strategy.
     * @see #objectIdStrategy MongoDB strategy (ObjectId generation).
     * @see #uuidStrategy PostgreSQL strategy (UUID generation).
     */
    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        boolean isMongoDB =
                Boolean.parseBoolean(System.getProperty("db.mongo", "true")); // Default to "mongo"
        if (isMongoDB) {
            return objectIdStrategy.getValue(attrType, attrAnnotations);
        }
        return uuidStrategy.getValue(attrType, attrAnnotations);
    }
}
