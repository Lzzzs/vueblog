package top.lzzzs.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.lzzzs.common.lang.R;

/**
 * 全局异常捕获
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * shiro异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(value = ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handler(ShiroException e) {
        log.error("shiro异常-----------------------");
        return R.e(401, e.getMessage(), null);
    }

    /**
     * 实体类校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handler(MethodArgumentNotValidException e) {
        log.error("实体数据校验异常-----------------------");
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return R.e(400, objectError.getDefaultMessage(), null);
    }


    /**
     * assert异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handler(IllegalArgumentException e) {
        log.error("assert异常-----------------------");
        return R.e(500, e.getMessage(), null);
    }


    /**
     * 运行时异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handler(RuntimeException e) {
        log.error("运行时异常-----------------------");
        return R.e(500, e.getMessage(), null);
    }
}
