package com.example.augen.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.augen.demo.service.Identifier;
import com.example.augen.demo.service.ProfitFactor;
import com.example.augen.demo.service.SpotPriceService;
import com.example.augen.demo.service.model.BitCoinBuySellResponse;
import com.example.augen.demo.service.model.Data;
import com.example.augen.demo.service.model.SpotPrice;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = DemoApplication.class)
public class BitcoinControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private SpotPriceService spotPriceService;
	@MockBean
	private ProfitFactor profitFactor;
	@MockBean
	private Identifier identifier;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void buyTestSuccessWithoutCurrencyParam() throws Exception {
		SpotPrice spotPrice = new SpotPrice();
		Data data = new Data();
		data.setAmount(100);
		data.setCurrency("NZD");
		spotPrice.setData(data);
		Mockito.when(spotPriceService.getSpotPrice("NZD")).thenReturn(spotPrice);
		Mockito.when(profitFactor.getProfitFactor()).thenReturn(1.0);
		Mockito.when(identifier.getIdentifier()).thenReturn((long) 1);

		MvcResult andReturn = mockMvc
				.perform(MockMvcRequestBuilders.get("/price/buy?amount=10").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();

		BitCoinBuySellResponse actual = objectMapper.readValue(andReturn.getResponse().getContentAsString(),
				BitCoinBuySellResponse.class);

		BitCoinBuySellResponse expected = new BitCoinBuySellResponse();
		expected.setAmount(10);
		expected.setId(1);
		expected.setProfitFactor(1);
		expected.setTotalPrice(1000);
		expected.setCurrency("NZD");
		expected.setSpotPrice(100);
		assertEquals(expected, actual);
	}

	@Test
	public void buyTestSuccess() throws Exception {
		SpotPrice spotPrice = new SpotPrice();
		Data data = new Data();
		data.setAmount(100);
		data.setCurrency("USD");
		spotPrice.setData(data);
		Mockito.when(spotPriceService.getSpotPrice("USD")).thenReturn(spotPrice);
		Mockito.when(profitFactor.getProfitFactor()).thenReturn(1.0);
		Mockito.when(identifier.getIdentifier()).thenReturn((long) 1);

		MvcResult andReturn = mockMvc.perform(
				MockMvcRequestBuilders.get("/price/buy?amount=10&currency=USD").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();

		BitCoinBuySellResponse actual = objectMapper.readValue(andReturn.getResponse().getContentAsString(),
				BitCoinBuySellResponse.class);

		BitCoinBuySellResponse expected = new BitCoinBuySellResponse();
		expected.setAmount(10);
		expected.setId(1);
		expected.setProfitFactor(1);
		expected.setTotalPrice(1000);
		expected.setCurrency("USD");
		expected.setSpotPrice(100);
		assertEquals(expected, actual);
	}

	@Test
	public void sellTestwithInvalidCurrency() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/price/sell?amount=10&currency=USDA")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();

	}
}
