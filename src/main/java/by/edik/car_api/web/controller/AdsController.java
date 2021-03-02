package by.edik.car_api.web.controller;

import by.edik.car_api.model.Condition;
import by.edik.car_api.service.impl.AdServiceImpl;
import by.edik.car_api.web.dto.AdCreatedDto;
import by.edik.car_api.web.dto.AdPatchedDto;
import by.edik.car_api.web.dto.AdUpdatedDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static by.edik.car_api.config.ServletConstants.CHARACTER_ENCODING;
import static by.edik.car_api.config.ServletConstants.CONTENT_TYPE;

@WebServlet("/api/v1/ads/")
public class AdsController extends HttpServletWithPatch {

    private final AdServiceImpl adService = AdServiceImpl.getInstance();
    private static final Logger LOG = Logger.getLogger(AdsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("GET method running.");
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));
        LOG.info("Transferred params:\n" + req.getQueryString());
        String json = mapper.writeValueAsString(adService.getAllShortInformationAds(page, size));
        writer.write(json);
        LOG.info("Data returned to the client:\n" + json);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("POST method running.");
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        AdCreatedDto adCreatedDto = AdCreatedDto.builder()
                .userId(Long.parseLong(req.getParameter("userId")))
                .year(Integer.parseInt(req.getParameter("year")))
                .brand(req.getParameter("brand"))
                .model(req.getParameter("model"))
                .engineVolume(Integer.parseInt(req.getParameter("engineVolume")))
                .condition(Condition.valueOf(req.getParameter("condition")))
                .mileage(Long.parseLong(req.getParameter("mileage")))
                .enginePower(Integer.parseInt(req.getParameter("enginePower")))
                .ownerPhoneNumbers(Arrays.asList(req.getParameterValues("ownerPhoneNumbers")))
                .pictureReferences(Arrays.asList(req.getParameterValues("pictureReferences")))
                .build();
        LOG.info("Transferred params:\n" + req.getQueryString());
        String json = mapper.writeValueAsString(adService.create(adCreatedDto));
        writer.write(json);
        LOG.info("Data returned to the client:\n" + json);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("PUT method running.");
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        AdUpdatedDto adUpdatedDto = AdUpdatedDto.builder()
                .adId(Long.parseLong(req.getParameter("adId")))
                .userId(Long.parseLong(req.getParameter("userId")))
                .year(Integer.parseInt(req.getParameter("year")))
                .brand(req.getParameter("brand"))
                .model(req.getParameter("model"))
                .engineVolume(Integer.parseInt(req.getParameter("engineVolume")))
                .condition(Condition.valueOf(req.getParameter("condition")))
                .mileage(Long.parseLong(req.getParameter("mileage")))
                .enginePower(Integer.parseInt(req.getParameter("enginePower")))
                .ownerName(req.getParameter("ownerName"))
                .ownerPhoneNumbers(Arrays.asList(req.getParameterValues("ownerPhoneNumbers")))
                .pictureReferences(Arrays.asList(req.getParameterValues("pictureReferences")))
                .build();
        LOG.info("Transferred params:\n" + req.getQueryString());
        String json = mapper.writeValueAsString(adService.update(adUpdatedDto));
        writer.write(json);
        LOG.info("Data returned to the client:\n" + json);
        writer.flush();
        writer.close();
    }

    @Override
    public void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("PATCH method running.");
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding(CHARACTER_ENCODING);
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        AdPatchedDto adPatchedDto = AdPatchedDto.builder()
                .adId(Long.parseLong(req.getParameter("adId")))
                .year(Integer.parseInt(req.getParameter("year")))
                .brand(req.getParameter("brand"))
                .model(req.getParameter("model"))
                .engineVolume(Integer.parseInt(req.getParameter("engineVolume")))
                .mileage(Long.parseLong(req.getParameter("mileage")))
                .enginePower(Integer.parseInt(req.getParameter("enginePower")))
                .build();
        LOG.info("Transferred params:\n" + req.getQueryString());
        String json = mapper.writeValueAsString(adService.updateAllowedFields(adPatchedDto));
        writer.write(json);
        LOG.info("Data returned to the client:\n" + json);
        writer.flush();
        writer.close();
    }

}
