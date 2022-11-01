package com.evision.archivalmanagementmsapi.controller;

import com.evision.archivalmanagementmsapi.domain.bo.ServiceResponseBo;
import com.evision.archivalmanagementmsapi.domain.enums.ServiceResponseRef;
import com.evision.archivalmanagementmsapi.domain.exceptions.ServerErrorException;
import com.evision.archivalmanagementmsapi.domain.exceptions.ValidationException;
import com.evision.archivalmanagementmsapi.domain.responses.BaseResponseDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Function;

@Slf4j
public class BaseController {
    public <TRequest, TResponse> BaseResponseDto<TResponse> execute(TRequest request, Function<TRequest, ServiceResponseBo<TResponse>> func, String actionName) {
        BaseResponseDto<TResponse> response = null;
        try{
            //TODO::CHECK LICENSE
            log.info("Begin Request: " + actionName);
            ServiceResponseBo<TResponse> result = func.apply(request);
            response = new BaseResponseDto<>(ServiceResponseRef.SUCCESS, result.getMessage(), result.getReturnObject());
        }
        catch (ValidationException ex) {
            log.error(ex.getMessage());
            response = new BaseResponseDto<>(ServiceResponseRef.VALIDATION_ERROR, ex.getMessage(), null);
        }
        catch (SecurityException ex) {
            log.error(ex.getMessage());
            response = new BaseResponseDto<>(ServiceResponseRef.SECURITY_ERROR, ex.getMessage(), null);
        }
        catch (ServerErrorException ex) {
            log.error(ex.getMessage());
            response = new BaseResponseDto<>(ServiceResponseRef.SERVER_ERROR, ex.getMessage(), null);
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
            response = new BaseResponseDto<>(ServiceResponseRef.SERVER_ERROR, "Error while trying to process request", null);
        }
        finally {
            Objects.requireNonNull(response).log();
            log.info("End Request: " + actionName);
        }
        return response;
    }

    public <TResponse> BaseResponseDto<TResponse> execute(Callable<ServiceResponseBo<TResponse>> func, String actionName) {
        BaseResponseDto<TResponse> response = null;
        try{
            log.info("Begin Request: " + actionName);
            ServiceResponseBo<TResponse> result = func.call();
            response = new BaseResponseDto<>(ServiceResponseRef.SUCCESS, result.getMessage(), result.getReturnObject());
        }
        catch (ValidationException ex) {
            response = new BaseResponseDto<>(ServiceResponseRef.VALIDATION_ERROR, ex.getMessage(), null);
        }
        catch (SecurityException ex) {
            response = new BaseResponseDto<>(ServiceResponseRef.SECURITY_ERROR, ex.getMessage(), null);
        }
        catch (ServerErrorException ex) {
            response = new BaseResponseDto<>(ServiceResponseRef.SERVER_ERROR, ex.getMessage(), null);
        }
        catch (Exception ex) {
            response = new BaseResponseDto<>(ServiceResponseRef.SERVER_ERROR, "Error while trying to process request", null);
        }
        finally {
            Objects.requireNonNull(response).log();
            log.info("End Request: " + actionName);
        }
        return response;
    }
}
