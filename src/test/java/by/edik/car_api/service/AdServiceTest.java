package by.edik.car_api.service;

import by.edik.car_api.db.DataSource;
import by.edik.car_api.model.Ad;
import by.edik.car_api.model.Condition;
import by.edik.car_api.model.User;
import by.edik.car_api.service.impl.AdServiceImpl;
import by.edik.car_api.service.impl.UserServiceImpl;
import by.edik.car_api.web.dto.AdDto;
import by.edik.car_api.web.dto.UserDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdServiceTest {

    private static final Integer YEAR = 1999;
    private static final String BRAND = "Lada";
    private static final String MODEL = "Kalina";
    private static final Integer ENGINE_VOLUME = 1600;
    private static final Long MILEAGE = 350555L;
    private static final Integer ENGINE_POWER = 80;
    private static final LocalDateTime CREATION_TIME = LocalDateTime.of(2021, 2, 8, 12, 20);
    private static final LocalDateTime EDITING_TIME = LocalDateTime.of(2021, 2, 15, 16, 20);

    AdServiceImpl adServiceImpl = AdServiceImpl.getInstance();
    UserServiceImpl userService = UserServiceImpl.getInstance();
    UserDto userDto;
    AdDto adDto;

    @BeforeEach
    void setUp() {
        userDto = userService.create(new User()
                .setUsername("test")
                .setEmail("test@tut.by")
                .setPassword("pass")
        );
        adDto = adServiceImpl.create(new Ad()
                .setUserId(userDto.getUserId())
                .setYear(YEAR)
                .setBrand(BRAND)
                .setModel(MODEL)
                .setEngineVolume(ENGINE_VOLUME)
                .setCondition(Condition.USED)
                .setMileage(MILEAGE)
                .setEnginePower(ENGINE_POWER)
                .setCreationTime(CREATION_TIME)
                .setEditingTime(EDITING_TIME)
        );
    }


    @AfterEach
    void tearDown() {
        truncateAll();
    }

    @Test
    void create() {
        Ad createdAd = adServiceImpl.create(Ad);
        assertTrue(createdAd.getAdId() > 0);
        assertTrue(createdAd.getUserId() > 0);
        assertEquals(YEAR, createdAd.getYear());
        assertEquals(BRAND, createdAd.getBrand());
        assertEquals(MODEL, createdAd.getModel());
        assertEquals(ENGINE_VOLUME, createdAd.getEngineVolume());
        assertEquals(Condition.USED, createdAd.getCondition());
        assertEquals(MILEAGE, createdAd.getMileage());
        assertEquals(ENGINE_POWER, createdAd.getEnginePower());
        assertEquals(CREATION_TIME, createdAd.getCreationTime());
        assertEquals(EDITING_TIME, createdAd.getEditingTime());
    }

    @Test
    void getById() {
        Ad foundedAd = adServiceImpl.getById(adDto.getAdId());
        assertTrue(foundedAd.getAdId() > 0);
        assertTrue(foundedAd.getUserId() > 0);
        assertEquals(YEAR, foundedAd.getYear());
        assertEquals(BRAND, foundedAd.getBrand());
        assertEquals(MODEL, foundedAd.getModel());
        assertEquals(ENGINE_VOLUME, foundedAd.getEngineVolume());
        assertEquals(Condition.USED, foundedAd.getCondition());
        assertEquals(MILEAGE, foundedAd.getMileage());
        assertEquals(ENGINE_POWER, foundedAd.getEnginePower());
        assertEquals(CREATION_TIME, foundedAd.getCreationTime());
        assertEquals(EDITING_TIME, foundedAd.getEditingTime());
    }

    @Test
    void getAll() {
        adServiceImpl.create(adDto
                .setBrand("JIGULI")
                .setModel("2107")
                .setCondition(Condition.NEW)
                .setYear(2020)
                .setEnginePower(0)
                .setEngineVolume(0)
                .setMileage(0L)
        );
        List<AdDto> foundedAds = adServiceImpl.getAll();
        assertEquals(foundedAds.size(), 2);

        Ad firstAd = foundedAds.get(0);
        assertTrue(firstAd.getAdId() > 0);
        assertTrue(firstAd.getUserId() > 0);
        assertEquals(YEAR, firstAd.getYear());
        assertEquals(BRAND, firstAd.getBrand());
        assertEquals(MODEL, firstAd.getModel());
        assertEquals(ENGINE_VOLUME, firstAd.getEngineVolume());
        assertEquals(Condition.USED, firstAd.getCondition());
        assertEquals(MILEAGE, firstAd.getMileage());
        assertEquals(ENGINE_POWER, firstAd.getEnginePower());
        assertEquals(CREATION_TIME, firstAd.getCreationTime());
        assertEquals(EDITING_TIME, firstAd.getEditingTime());

        Ad secondAd = foundedAds.get(1);
        assertTrue(secondAd.getAdId() > 0);
        assertTrue(secondAd.getUserId() > 0);
        assertEquals(2020, secondAd.getYear());
        assertEquals("JIGULI", secondAd.getBrand());
        assertEquals("2107", secondAd.getModel());
        assertEquals(0, secondAd.getEngineVolume());
        assertEquals(Condition.NEW, secondAd.getCondition());
        assertEquals(0, secondAd.getMileage());
        assertEquals(0, secondAd.getEnginePower());
        assertEquals(CREATION_TIME, secondAd.getCreationTime());
        assertEquals(EDITING_TIME, secondAd.getEditingTime());
    }

    @Test
    void update() {
        Ad foundedAd = adServiceImpl.getById(ad.getAdId())
                .setYear(2010)
                .setBrand("Ford")
                .setModel("Focus")
                .setEngineVolume(2000)
                .setCondition(Condition.DAMAGED)
                .setMileage(10999L)
                .setEnginePower(150)
                .setCreationTime(CREATION_TIME)
                .setEditingTime(EDITING_TIME);
        adServiceImpl.update(foundedAd);
        Ad updatedAd = adServiceImpl.getById(foundedAd.getAdId());
        assertTrue(updatedAd.getAdId() > 0);
        assertTrue(updatedAd.getUserId() > 0);
        assertEquals(2010, updatedAd.getYear());
        assertEquals("Ford", updatedAd.getBrand());
        assertEquals("Focus", updatedAd.getModel());
        assertEquals(2000, updatedAd.getEngineVolume());
        assertEquals(Condition.DAMAGED, updatedAd.getCondition());
        assertEquals(10999, updatedAd.getMileage());
        assertEquals(150, updatedAd.getEnginePower());
        assertEquals(CREATION_TIME, updatedAd.getCreationTime());
        assertEquals(EDITING_TIME, updatedAd.getEditingTime());
    }

    @Test
    void delete() {
        adServiceImpl.delete(ad.getAdId());
        assertNull(adServiceImpl.getById(ad.getAdId()));
    }

    @Test
    void updateAllowedFields() {
        Ad foundedAd = adServiceImpl.getById(ad.getAdId())
                .setYear(2010)
                .setBrand("Ford")
                .setModel("Focus")
                .setEngineVolume(2000)
                .setMileage(10999L)
                .setEnginePower(150);
        adServiceImpl.updateAllowedFields(foundedAd);
        Ad updatedAd = adServiceImpl.getById(foundedAd.getAdId());
        assertTrue(updatedAd.getAdId() > 0);
        assertTrue(updatedAd.getUserId() > 0);
        assertEquals(2010, updatedAd.getYear());
        assertEquals("Ford", updatedAd.getBrand());
        assertEquals("Focus", updatedAd.getModel());
        assertEquals(2000, updatedAd.getEngineVolume());
        assertEquals(10999, updatedAd.getMileage());
        assertEquals(150, updatedAd.getEnginePower());
    }

    @SneakyThrows
    private void truncateAll() {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "truncate pictures, ads, user_information, user_phones, users;"
        );
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }
}
