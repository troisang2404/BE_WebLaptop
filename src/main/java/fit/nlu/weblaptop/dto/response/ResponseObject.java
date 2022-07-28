package fit.nlu.weblaptop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObject {
    private String status;   // success | failed
    private String message;     // Thông điệp
    private Object object;;         // return Object

    public ResponseObject() {
    }

    public ResponseObject(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }


//    public ResponseObject<Object> failed() {
//        return new ResponseObject<>("failed", "Lỗi hệ thống", null);
//    }
//
//    public ResponseObject<Object> failed(String msg) {
//        return new ResponseObject<>("failed", msg, null);
//    }
//
//    public ResponseObject<Object> success() {
//        return new ResponseObject<>("success", "Thành công", null);
//    }
//
//    public ResponseObject<Object> success(Object object) {
//        return new ResponseObject<>("failed", "Thành công", object);
//    }
//
//    public ResponseObject<Object> success(String message, Object object) {
//        return new ResponseObject<>("success", message, object);
//    }
}
