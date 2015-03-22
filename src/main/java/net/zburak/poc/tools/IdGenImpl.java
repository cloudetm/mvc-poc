package net.zburak.poc.tools;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * Created by buraq
 */
@Component
public class IdGenImpl implements IdGenerator<String> {
    @Override
    public String generate() {
        return new ObjectId().toString();//objectId for the POC
    }
}
