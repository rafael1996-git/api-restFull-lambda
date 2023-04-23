package com.huellazteca.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.huellazteca.core.CoreLambdaHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HuellAztLambdaHandler extends CoreLambdaHandler {
    static {
        try {
            CoreLambdaHandler.initHandler(HuellAztApplication.class);
        } catch (Exception e) {
            System.out.println(new StringBuilder("Error al inicializar el Handler").append(e.toString()));
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        this.startHandler(inputStream, outputStream, context);
    }

}
