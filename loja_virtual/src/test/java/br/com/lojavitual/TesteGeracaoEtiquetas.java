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
		RequestBody body = RequestBody.create(mediaType, "{    \"service\":3,    \"agency\":49,    \"from\":{       \"name\":\"Nome do remetente\",       \"phone\":\"53984470102\",       \"email\":\"contato@melhorenvio.com.br\",       \"document\":\"16571478358\",       \"company_document\":\"89794131000100\",       \"state_register\":\"123456\",       \"address\":\"Endereço do remetente\",       \"complement\":\"Complemento\",       \"number\":\"1\",       \"district\":\"Bairro\",       \"city\":\"São Paulo\",       \"country_id\":\"BR\",       \"postal_code\":\"01002001\",       \"note\":\"observação\"    },    \"to\":{       \"name\":\"Nome do destinatário\",       \"phone\":\"53984470102\",       \"email\":\"contato@melhorenvio.com.br\",       \"document\":\"25404918047\",       \"company_document\":\"07595604000177\",       \"state_register\":\"123456\",       \"address\":\"Endereço do destinatário\",       \"complement\":\"Complemento\",       \"number\":\"2\",       \"district\":\"Bairro\",       \"city\":\"Porto Alegre\",       \"state_abbr\":\"RS\",       \"country_id\":\"BR\",       \"postal_code\":\"90570020\",       \"note\":\"observação\"    },    \"products\":[       {          \"name\":\"Papel adesivo para etiquetas 1\",          \"quantity\":3,          \"unitary_value\":100.00       },       {          \"name\":\"Papel adesivo para etiquetas 2\",          \"quantity\":1,          \"unitary_value\":700.00       }    ],    \"volumes\":[       {          \"height\":15,          \"width\":20,          \"length\":10,          \"weight\":3.5       }    ],    \"options\":{       \"insurance_value\":1000.00,       \"receipt\":false,       \"own_hand\":false,       \"reverse\":false,       \"non_commercial\":false,       \"invoice\":{          \"key\":\"31190307586261000184550010000092481404848162\"       },       \"platform\":\"Nome da Plataforma\",       \"tags\":[          {             \"tag\":\"Identificação do pedido na plataforma, exemplo: 1000007\",             \"url\":\"Link direto para o pedido na plataforma, se possível, caso contrário pode ser passado o valor null\"          }       ]    } }");
		Request request = new Request.Builder()
		  .url(ApiTokenIntregracao.URL_MELHOR_ENVIO+"/api/v2/me/cart")
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
