package com.rakoon.backend.util;

import com.rakoon.backend.model.entity.Address;
import com.rakoon.backend.model.entity.City;
import com.rakoon.backend.model.entity.Consumer;
import com.rakoon.backend.model.entity.Establishment;
import com.rakoon.backend.model.entity.Province;
import com.rakoon.backend.model.entity.Sector;
import com.rakoon.backend.model.entity.User;
import com.rakoon.backend.model.entity.WorkDay;
import com.rakoon.backend.model.entity.WorkingDay;
import com.rakoon.backend.model.view.*;
import com.rakoon.backend.security.AuthCredentials;
import com.rakoon.backend.security.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestEntityFactory {

    static String password = new BCryptPasswordEncoder().encode("rakoon");

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = new UserDetailsImpl(getUserTest());
        return new InMemoryUserDetailsManager(user);
    }
    public static Consumer getConsumer(){
        return Consumer.builder()
                .consumerId(1L)
                .name("Joaquin")
                .lastName("Alvarez")
                .birthdate(LocalDate.of(2023,12,12))
                .phone("3534665665")
                .build();
    }

    public static User getUser(){
        return User.builder()
                .userId(1L)
                .email("jalvarez@gmail.com")
                .password(password)
                .build();
    }
    public static User getUserTest(){
        return User.builder()
                .userId(912L)
                .email("spring-test")
                .password(password)
                .build();
    }

    public static Sector getSector(){
        return Sector.builder()
                .id(1L)
                .name("Restaurant")
                .description("Description")
                .build();
    }
    public static WorkDay getWorkDay(){
        return WorkDay.builder()
                .id(1L)
                .workingDay(WorkingDay.MONDAY)
                .timePickUpFrom("10:00")
                .timePickUpTo("20:00")
                .build();
    }
    public static List<WorkDay> getWorkDayList(){
        List list = new ArrayList<>();
        list.add(getWorkDay());
        return list;
    }public static List<WorkDayDto> getWorkDayDtoList(){
        List list = new ArrayList<>();
        list.add(getWorkDayDto());
        return list;
    }

    public static AuthCredentials getAuthCredentials(){
        return AuthCredentials.builder()
                .email("jalvarez@gmail.com")
                .password("rakoon")
                .build();
    }
    public static Establishment getEstablishment(){
        return Establishment.builder()
                .id(1L)
                .name("Ala manchada")
                .cuit("2030405060")
                .description("Description")
                .phone("154265376")
                .sector(getSector())
                .workDay(getWorkDayList())
                .address(getAddress())
                .build();
    }
    public static ConsumerDto getConsumerDto(){
        return ConsumerDto.builder()
                .userId(1L)
                .email("jalvarez@gmail.com")
                .password(password)
                .dateRegistration(LocalDate.of(2023,12,12))
                .name("Joaquin")
                .lastName("Alvarez")
                .birthdate(LocalDate.of(2023,12,12))
                .phone("3534665665")
                .build();
    }
    public static EstablishmentDto getEstablishmentDto(){
        return EstablishmentDto.builder()
                .id(1L)
                .name("Ala manchada")
                .cuit("2030405060")
                .description("Description")
                .phone("154265376")
                .address(getAddressDto())
                .addressInput("Las heras,Villa,Cordoba")
                .build();
    }
    public static SectorDto getSectorDto(){
        return SectorDto.builder()
                .id(1L)
                .name("Restaurant")
                .description("Description")
                .build();
    }
    public static WorkDayDto getWorkDayDto(){
        return WorkDayDto.builder()
                .id(1L)
                .workingDay(WorkingDay.MONDAY)
                .timePickUpFrom("10:00")
                .timePickUpTo("20:00")
                .build();
    }
    public static ProvinceDto getProvinceDto(){
        return ProvinceDto.builder()
                .id(1L)
                .name("Cordoba")
                .code("CBA")
                .build();
    }public static Province getProvince(){
        return Province.builder()
                .id(1L)
                .name("Cordoba")
                .code("CBA")
                .build();
    }
    public static CityDto getCityDto(){
        return CityDto.builder()
                .id(1L)
                .name("Villa Maria")
                .code("5900")
                .build();
    }public static City getCity(){
        return City.builder()
                .id(1L)
                .name("Villa Maria")
                .code("5900")
                .build();
    }
    public static AddressDto getAddressDto(){
        return AddressDto.builder()
                .id(1L)
                .street("Street")
                .numberStreet("912")
                .floor("3")
                .numberApartment("7")
                .build();
    }public static Address getAddress(){
        return Address.builder()
                .id(1L)
                .street("Street")
                .numberStreet("912")
                .floor("3")
                .numberApartment("7")
                .build();
    }
    public static PackCardDto getPackCardDto(){
        return PackCardDto.builder()
                .id(1L)
                .templateName("Pack Sorpresa")
                .templateImage("url.image.sorpresa")
                .establishmentName("Ala Manchada")
                .establishmentProfileImage("url.image.profile")
                .sectorName("Resto Bar")
                .qualificationEstablishment(4.3)
                .timePickUpTo("20:00")
                .timePickUpFrom("16:00")
                .packPrice(450.0)
                .packDiscountRate(12.3)
                .quantityPacksAvailable(4)
                .build();
    }
}
