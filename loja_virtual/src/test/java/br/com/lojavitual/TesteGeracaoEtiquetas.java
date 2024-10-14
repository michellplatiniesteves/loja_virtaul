package br.com.lojavitual;

import java.io.IOException;

import br.com.lojavirtual.apitransporte.enums.ApiTokenIntregracao;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TesteGeracaoEtiquetas {

	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\"orders\":[\"string\"]}");
		Request request = new Request.Builder()
		  .url(ApiTokenIntregracao.URL_MELHOR_ENVIO+"api/v2/me/shipment/generate")
		  .post(body)
		  .addHeader("Accept", "application/json")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Authorization", "Bearer "+ApiTokenIntregracao.TOKEN_SANDBOX)
		  .addHeader("User-Agent", "michellplatini@gmail.com")
		  .build();

		Response response = client.newCall(request).execute();
		
		System.out.println(response.body().string());


		

	}

}
