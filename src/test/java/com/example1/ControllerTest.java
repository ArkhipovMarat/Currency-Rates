package com.example1;

import com.example1.controller.ApplicationController;
import com.example1.service.CompareRatesService;
import com.example1.service.GiphyLookerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Objects;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ControllerTest {
    @MockBean
    CompareRatesService compareRatesServiceMock;

    @MockBean
    GiphyLookerService giphyLookerServiceMock;

    @Autowired
    private MockMvc mockMvc;

    private static final String CURRENCY = "RUB";
    private static final String TAG = "broke";
    private static final String GIPHY_LOOKER_SERVICE_RESULT = "https://media1.giphy.com/media/VitmmLiKYTd3MIZXM7/giphy.gif";
    private static final int COMPARE_SERVICE_RESULT = -1;
    private static final String REQUEST = "/rates/RUB";
    private static final String VIEW_NAME = "index";
    private static final String MODEL_ATTRIBUTE = "gif";

    @Test
    public void main() throws Exception {
        Mockito.when(compareRatesServiceMock.compareRates(CURRENCY)).thenReturn(COMPARE_SERVICE_RESULT);
        Mockito.when(giphyLookerServiceMock.searchGiphy(TAG)).thenReturn(GIPHY_LOOKER_SERVICE_RESULT);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(REQUEST);
        ResultActions result = mockMvc.perform(request);

        result.andExpect(MockMvcResultMatchers.view().name(VIEW_NAME));

        String modelAndViewValue = (String) Objects.requireNonNull(result.andReturn().getModelAndView()).
                getModel().get(MODEL_ATTRIBUTE);
        Assertions.assertEquals(GIPHY_LOOKER_SERVICE_RESULT, modelAndViewValue);
    }
}
