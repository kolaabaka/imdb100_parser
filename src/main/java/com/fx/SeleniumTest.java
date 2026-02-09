package com.fx;

import com.fx.dto.FilmDto;
import com.fx.service.CsvService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumTest
{
    public static void main( String[] args )
    {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/search/title/?groups=top_100&sort=user_rating,desc");

        WebElement fifMore = driver.findElement(By.xpath("//button[@class=\"ipc-btn ipc-btn--single-padding ipc-btn--center-align-content ipc-btn--default-height ipc-btn--core-base ipc-btn--theme-base ipc-btn--button-radius ipc-btn--on-accent2 ipc-text-button ipc-see-more__button\"]"));
        fifMore.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
            By.xpath("//li[@class='ipc-metadata-list-summary-item']"),
            50
        ));

        List<WebElement> input = driver.findElements(By.xpath("//li[@class=\"ipc-metadata-list-summary-item\"]"));

        List<FilmDto> films = new ArrayList<>();

        for(WebElement elem: input) {
            try{
                FilmDto film = new FilmDto();
                film.setName(elem.findElement(By.xpath(".//h3[@class='ipc-title__text']")).getText());
                film.setScore(elem.findElement(By.xpath(".//span[contains(@class, 'metacritic-score-box')]")).getText());
                films.add(film);
            }
            catch (Exception e){
            }
        }

        try {
            CsvService.writeCsvFile(films);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.close();
    }
}
