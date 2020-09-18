package by.kvach.lightBase.util;

import by.kvach.lightBase.model.Candidate;
import by.kvach.lightBase.model.Contact;
import by.kvach.lightBase.model.ContactType;
import by.kvach.lightBase.model.Vacancy;
import by.kvach.lightBase.service.dbangine.DBConnect;
import by.kvach.lightBase.service.dbangine.Query;
import com.codeborne.selenide.Condition;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class Util {

    /**
     * This method allows to create new Candidate for UI.
     * random data from Data Base
     *
     * @param contactType {@link ContactType}
     * @return Candidate
     */
    public static Candidate createRandomCandidate(ContactType contactType) {

        return new Candidate(
                createRandomName(8),
                getRandomFromDB(Query.getRandomVacancy()),
                getRandomFromDB(Query.getRandomPosition()),
                getRandomFromDB(Query.getRandomLevel()),
                createRandomNumber(9999),
                getRandomFromDB(Query.getRandomExperience()),
                getRandomFromDB(Query.getRandomHR()),
                getRandomFromDB(Query.getRandomEnglishLevel()),
                createRandomContact(contactType),
                getRandomFromDB(Query.getRandomSourceType()),
                getRandomFromDB(Query.getRandomSourceContactType()),
                createRandomWord(18),
                getRandomFromDB(Query.getRandomCommunicationStatus()));

    }

    /**
     * This method allows to create new Vacancy.
     * random data from Data Base
     *
     * @return Vacancy
     */
    public static Vacancy createRandomVacancy() {

        return new Vacancy(
                createRandomName(8),
                getRandomFromDB(Query.getRandomPosition()),
                getRandomFromDB(Query.getRandomHR()),
                createRandomWord(8),
                String.valueOf(createRandomNumber(99999)),
                createRandomWord(30),
                createRandomWord(20),
                getRandomFromDB(Query.getRandomInterviewer()),
                createRandomWord(30),
                getDate(),
                getRandomFromDB(Query.getRandomLevel()),
                getRandomFromDB(Query.getRandomEnglishLevel()));

    }

    /**
     * This method allows to create new Contact.
     * random data from Data Base
     *
     * @param type {@link ContactType}
     * @return Contact
     */
    public static Contact createRandomContact(ContactType type) {

        String value;

        switch (type) {
            case PHONE:
                value = String.valueOf(createRandomNumber(999999999));
                break;
            case EMAIL:
                value = createRandomWord(5) + "@" + createRandomWord(4) + ".com";
                break;
            case SKYPE:
                value = createRandomWord(5);
                break;
            case LINKEDIN:
                value = createRandomWord(5) + "." + createRandomWord(5);
                break;
            case TELEGRAM:
                value = createRandomWord(7);
                break;
            default:
                value = createRandomWord(5);
                break;
        }

        return new Contact(type, value);
    }

    /**
     * This method allows to create new date.
     *
     * @return String Data
     */
    private static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    /**
     * This method allows to connect to Data Base
     * and execute Query
     *
     * @return String Data
     */
    private static String getRandomFromDB(String query) {

        var connect = new DBConnect();
        return String.valueOf(connect.getStringDBDData(query));
    }

    /**
     * This method allows to get Int expression from URL string
     *
     * @return int
     */
    public static int getCandidateId(String string) {

        Pattern pat = Pattern.compile("\\d+");
        Matcher matcher = pat.matcher(string);
        int result;
        if (matcher.find()) {
            result = Integer.parseInt(matcher.group());
        } else {
            throw new RuntimeException("Not Found digital expression in - " + string);
        }
        return result;
    }

    /**
     * This method allows to create random Word
     *
     * @param maxValue - max length new word
     * @return String
     */
    public static String createRandomWord(int maxValue) {
        return randomAlphabetic(2, maxValue);
    }

    /**
     * This method allows to create random name
     * name starts with aqa.Denis
     *
     * @param maxValue - max length new word
     * @return String
     */
    private static String createRandomName(int maxValue) {
        return "aqa.Denis" + randomAlphabetic(7, maxValue);
    }

    /**
     * This method allows to create random number
     *
     * @param max - max length new number
     * @return int
     */
    private static int createRandomNumber(int max) {
        int minValue = 0;
        return minValue + (int) (Math.random() * (max - minValue));
    }

    /**
     * This method allows to get New .txt File With String data
     *
     * @param text - String text for writing
     * @return File
     */
    public static File writeToFile(String text) {

        File file = new File("test.txt");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_16));
            out.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!(out == null)) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * Waiter
     */
    public static void waitDynamicDisappears() {
        $("#dynamic").waitUntil(Condition.disappear, 4000);
    }

}
