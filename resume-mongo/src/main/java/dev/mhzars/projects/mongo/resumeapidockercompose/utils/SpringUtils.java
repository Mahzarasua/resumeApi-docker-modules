package dev.mhzars.projects.mongo.resumeapidockercompose.utils;

import dev.mhzars.projects.commons.resumeapidockercompose.exception.CustomBadRequestException;
import dev.mhzars.projects.commons.resumeapidockercompose.exception.ExceptionBody;
import dev.mhzars.projects.commons.resumeapidockercompose.utils.CommonSpringUtils;
import java.util.Collections;
import org.bson.types.ObjectId;

public class SpringUtils extends CommonSpringUtils {
    private SpringUtils() {
        super();
    }

    public static ObjectId validateObjectId(String id) {
        try {
            return new ObjectId((id == null || id.isEmpty()) ? "" : id);
        } catch (IllegalArgumentException e) {
            ExceptionBody.ErrorDetails errorDetails =
                    new ExceptionBody.ErrorDetails(
                            "id",
                            String.format(
                                    "Value provided: %s cannot be converted to ObjectId", id));
            throw new CustomBadRequestException(
                    Collections.singletonList(errorDetails), "Conversion Error");
        }
    }
}
