package de.htwberlin.mae.exception;

import java.io.IOException;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import de.htwberlin.mae.model.ErrorInformation;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {
    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    	String timestamp = DateTime.now().toString();
		String type = "about:blank";
		String title = value.getOAuth2ErrorCode();
		HttpStatus status = HttpStatus.valueOf(value.getHttpErrorCode());
		String detail = value.getLocalizedMessage();
		String instance = "undefined";
		ErrorInformation errorInformation = new ErrorInformation(timestamp, type, title, status, detail, instance);
    	gen.writeObject(errorInformation);
    }


}
