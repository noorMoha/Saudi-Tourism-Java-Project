package com.tour.sauditourist;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Main Application
public class SaudiTourist {
    private static String language = "English"; // Default language
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        selectLanguage();
         if (!performLogin()) {
            System.out.println(getLocalizedMessage("Login failed. Exiting application.", "فشل تسجيل الدخول. يتم إنهاء التطبيق."));
            return;
        }
//        displayWelcomeMessage();

        List<TouristRegion> regions = createRegions();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n" + getLocalizedMessage("Choose a region or view help:", "اختر منطقة أو صفحة المساعدة:"));
            displayRegions(regions);
            System.out.println((regions.size() + 1) + ". " + getLocalizedMessage("Help", "مساعدة"));
            System.out.println("0. " + getLocalizedMessage("Exit", "خروج"));
            System.out.print("> ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                exit = true;
            } else if (choice > 0 && choice <= regions.size()) {
                displayRegionOptions(regions.get(choice - 1));
            } else if (choice == regions.size() + 1) {
                displayHelp();
            } else {
                System.out.println(getLocalizedMessage("Invalid choice.", "اختيار غير صالح."));
            }
        }
    }

    private static void selectLanguage() {
        System.out.println("Please select your language: 1. English  2. عربي");
        int langChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        language = (langChoice == 2) ? "Arabic" : "English";
    }
private static boolean performLogin() {
        System.out.println(getLocalizedMessage("Login", "تسجيل الدخول"));
        System.out.print(getLocalizedMessage("Enter username: ", "أدخل اسم المستخدم: "));
        String username = scanner.nextLine();

        System.out.print(getLocalizedMessage("Enter password: ", "أدخل كلمة المرور: "));
        String password = scanner.nextLine();

        // Simulating a basic login check
        String validUsername = "user";
        String validPassword = "user123";

        if (username.equals(validUsername) && password.equals(validPassword)) {
            System.out.println(getLocalizedMessage("Login successful.", "تم تسجيل الدخول بنجاح."));
            displayWelcomeMessage(validUsername);
            return true;
        } else {
            System.out.println(getLocalizedMessage("Invalid username or password.", "اسم مستخدم أو كلمة مرور غير صحيحة."));
            return false;
        }
    }
    private static void displayWelcomeMessage(String name) {
        if (language.equals("Arabic")) {
            System.out.println("مرحبا ًا بك في تطبيق السياحة في السعودية!");
        } else {
            System.out.println("Welcome "+name+" to Saudi Tourism App!");
        }
    }

private static List<TouristRegion> createRegions() {
    List<TouristRegion> regions = new ArrayList<>();
    
    // Existing regions
    regions.add(new TouristRegion("Riyadh", Arrays.asList("Kingdom Tower", "National Museum")));
    regions.add(new TouristRegion("Jeddah", Arrays.asList("Corniche", "Al-Balad Historic District")));


    // Adding Abha region with real places
    regions.add(new TouristRegion("Abha", Arrays.asList(
        "Abha Palace Dam",
        "Al-Soudah Park",
        "Habala Village",
        "Shada Palace",
        "Green Mountain"
    )));

    // Adding northern regions of Saudi Arabia with real places
    regions.add(new TouristRegion("Tabuk", Arrays.asList(
        "Al Disah Valley",
        "Tabuk Castle",
        "Al-Zareeb Fort",
        "Maghaer Shuaib"
    )));
    regions.add(new TouristRegion("Al-Jouf", Arrays.asList(
        "Marid Castle",
        "Domat Al-Jandal",
        "Za'abal Castle",
        "Sakaka Fort"
    )));
    regions.add(new TouristRegion("Hail", Arrays.asList(
        "A'arif Fort",
        "Jubbah Rock Art",
        "Hail Museum",
        "Qishlah Palace"
    )));

    return regions;
}


    private static void displayRegions(List<TouristRegion> regions) {
        for (int i = 0; i < regions.size(); i++) {
            System.out.println((i + 1) + ". " + regions.get(i).getName());
        }
    }

    private static void displayRegionOptions(TouristRegion region) {
        System.out.println("\n" + getLocalizedMessage("Tourist places in", "الأماكن السياحية في") + " " + region.getName() + ":");
        List<String> attractions = region.getAttractions();

        for (int i = 0; i < attractions.size(); i++) {
            System.out.println((i + 1) + ". " + attractions.get(i));
        }
        System.out.print("> ");
        int attractionChoice = scanner.nextInt();
        scanner.nextLine();

        if (attractionChoice >= 1 && attractionChoice <= attractions.size()) {
            String attraction = attractions.get(attractionChoice - 1);
            System.out.println(getLocalizedMessage("You selected", "لقد اخترت") + ": " + attraction);
            displayServices(region);
        } else {
            System.out.println(getLocalizedMessage("Invalid choice.", "اختيار غير صالح."));
        }
    }

    private static void displayServices(TouristRegion region) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            System.out.println("\n" + getLocalizedMessage("Available services:", "الخدمات المتوفرة:"));
            System.out.println("1. " + getLocalizedMessage("List of hotels", "قائمة الفنادق"));
            System.out.println("2. " + getLocalizedMessage("List of tourist guides", "قائمة المرشدين السياحيين"));
            System.out.println("3. " + getLocalizedMessage("Transportation options", "خيارات النقل"));
            System.out.println("4. " + getLocalizedMessage("Currency converter", "محول العملات"));
            System.out.println("5. " + getLocalizedMessage("Weather conditions", "أحوال الطقس"));
            System.out.println("0. " + getLocalizedMessage("Back to main menu", "العودة إلى القائمة الرئيسية"));
            System.out.print("> ");

            int serviceChoice = scanner.nextInt();
            scanner.nextLine();

            switch (serviceChoice) {
                case 1:
                    displayHotels(region);
                    break;
                case 2:
                    displayTouristGuides(region.getName());
                    break;
                case 3:
                    displayTransportOptions();
                    break;
                case 4:
                    displayCurrencyConverter();
                    break;
                case 5:
                    displayWeather(region);
                    break;
                case 0:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println(getLocalizedMessage("Invalid choice.", "اختيار غير صالح."));
            }
        }
    }


private static void displayHotels(TouristRegion region) {
    System.out.println(getLocalizedMessage("Hotels in", "الفنادق في") + " " + region.getName() + ":");
    switch (region.getName()) {
        case "Riyadh":
            System.out.println("- " + getLocalizedMessage("Ritz-Carlton Riyadh", "ريتز كارلتون الرياض"));
            System.out.println("- " + getLocalizedMessage("Four Seasons Hotel Riyadh", "فور سيزونز الرياض"));
            break;
        case "Jeddah":
            System.out.println("- " + getLocalizedMessage("Jeddah Hilton", "جدة هيلتون"));
            System.out.println("- " + getLocalizedMessage("Rosewood Jeddah", "روزوود جدة"));
            break;
        case "Abha":
            System.out.println("- " + getLocalizedMessage("Abha Palace Hotel", "فندق قصر أبها"));
            System.out.println("- " + getLocalizedMessage("Blue Inn Boutique Hotel", "فندق بلو إن بوتيك"));
            break;
        case "Tabuk":
            System.out.println("- " + getLocalizedMessage("Holiday Inn Tabuk", "هوليداي إن تبوك"));
            System.out.println("- " + getLocalizedMessage("Hilton Garden Inn Tabuk", "هيلتون جاردن إن تبوك"));
            break;
        case "Al-Jouf":
            System.out.println("- " + getLocalizedMessage("Raoum Inn Sakaka", "روم إن سكاكا"));
            System.out.println("- " + getLocalizedMessage("Al Nusl Hotel", "فندق النزل"));
            break;
        case "Hail":
            System.out.println("- " + getLocalizedMessage("Millennium Hail Hotel", "فندق ميلينيوم حائل"));
            System.out.println("- " + getLocalizedMessage("Boudl Hail", "بودل حائل"));
            break;
        default:
            System.out.println("- " + getLocalizedMessage("No hotels available", "لا توجد فنادق متاحة"));
            break;
    }
}


private static void displayTouristGuides(String region) {
    System.out.println(getLocalizedMessage("Tourist guides available:", "المرشدين السياحيين المتوفرين:"));
    
    // Display guides based on the selected region
    switch (region) {
        case "Riyadh":
            System.out.println(getLocalizedMessage("Riyadh:", "الرياض:"));
            System.out.println("- " + getLocalizedMessage("Fahad Al-Mutairi", "فهد المطيري"));
            System.out.println("- " + getLocalizedMessage("Abdullah Al-Qahtani", "عبدالله القحطاني"));
            break;
        
        case "Jeddah":
            System.out.println(getLocalizedMessage("Jeddah:", "جدة:"));
            System.out.println("- " + getLocalizedMessage("Ali Al-Amoudi", "علي العمودي"));
            System.out.println("- " + getLocalizedMessage("Hassan Al-Bakri", "حسن البكري"));
            break;
        
        case "Abha":
            System.out.println(getLocalizedMessage("Abha:", "أبها:"));
            System.out.println("- " + getLocalizedMessage("Ahmed Al-Shahrani", "أحمد الشهراني"));
            System.out.println("- " + getLocalizedMessage("Saleh Al-Asiri", "صالح العسيري"));
            break;
        
        case "Tabuk":
            System.out.println(getLocalizedMessage("Tabuk:", "تبوك:"));
            System.out.println("- " + getLocalizedMessage("Mohammed Al-Ruwaili", "محمد الرويلي"));
            System.out.println("- " + getLocalizedMessage("Khalid Al-Otaibi", "خالد العتيبي"));
            break;
        
        case "Al-Jouf":
            System.out.println(getLocalizedMessage("Al-Jouf:", "الجوف:"));
            System.out.println("- " + getLocalizedMessage("Sami Al-Mohanna", "سامي المهنا"));
            System.out.println("- " + getLocalizedMessage("Faisal Al-Nashmi", "فيصل النشمي"));
            break;
        
        case "Hail":
            System.out.println(getLocalizedMessage("Hail:", "حائل:"));
            System.out.println("- " + getLocalizedMessage("Majed Al-Anazi", "ماجد العنزي"));
            System.out.println("- " + getLocalizedMessage("Badr Al-Harbi", "بدر الحربي"));
            break;
        
        default:
            System.out.println(getLocalizedMessage("No guides available for this region.", "لا توجد مرشدين سياحيين لهذا المنطقة."));
            break;
    }
}



    private static void displayTransportOptions() {
        System.out.println(getLocalizedMessage("Transportation options:", "خيارات النقل:"));
        System.out.println("- " + getLocalizedMessage("Taxi", "سيارة أجرة") + " (50 SAR)");
        System.out.println("- " + getLocalizedMessage("Bus", "حافلة") + " (10 SAR)");
    }

    private static void displayCurrencyConverter() {
        System.out.print(getLocalizedMessage("Enter amount in SAR:", "أدخل المبلغ بالريال السعودي:") + " ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println(getLocalizedMessage("Equivalent in USD:", "ما يعادل بالدولار الأمريكي:") + " " + (amount * 0.27));
    }

    private static void displayWeather(TouristRegion region) {
        Weather weather = new Weather();
        weather.updateWeather(region.getName());
        weather.displayWeather();
    }

private static void displayHelp() {
    Help help = new Help(
        getLocalizedMessage("Tourist Guide", "دليل سياحي"),
        getLocalizedMessage("App instructions", "تعليمات التطبيق"),
        new Date(),
        getLocalizedMessage("Follow the instructions carefully.", "اتبع التعليمات بعناية."),
        language
    );

    boolean backToMainMenu = false;
    while (!backToMainMenu) {
        System.out.println("\n" + getLocalizedMessage("Help Options:", "خيارات المساعدة:"));
        System.out.println("1. " + getLocalizedMessage("Instructions", "تعليمات"));
        System.out.println("2. " + getLocalizedMessage("Emergency Contact Info", "معلومات الاتصال بالطوارئ"));
        System.out.println("0. " + getLocalizedMessage("Back to main menu", "العودة إلى القائمة الرئيسية"));
        System.out.print("> ");

        int helpChoice = scanner.nextInt();
        scanner.nextLine();

        switch (helpChoice) {
            case 1:
                help.showHelp();
                break;
            case 2:
                displayEmergencyInfo();
                break;
            case 0:
                backToMainMenu = true;
                break;
            default:
                System.out.println(getLocalizedMessage("Invalid choice.", "اختيار غير صالح."));
        }
    }
}

private static void displayEmergencyInfo() {
    // Create an Emergency object with localized messages
    Emergency emergency = new Emergency(
        getLocalizedMessage("Emergency Services", "خدمات الطوارئ"),
        getLocalizedMessage("Critical contact numbers for emergencies.", "أرقام الاتصال الحيوية للطوارئ."),
        new Date(),
        getLocalizedMessage("+123456789", "+123456789") // Example contact number (localized if needed)
    );

    // Call the overridden showInfo method to display detailed emergency info
    emergency.showInfo();

    // Call the showContact method to display emergency contact numbers
    emergency.showContact();
}



    private static String getLocalizedMessage(String english, String arabic) {
        return language.equals("Arabic") ? arabic : english;
    }
}

// Classes and Relationships
class TouristRegion {
    private String name;
    private List<String> attractions;

    public TouristRegion(String name, List<String> attractions) {
        this.name = name;
        this.attractions = attractions;
    }

    public String getName() {
        return name;
    }

    public List<String> getAttractions() {
        return attractions;
    }
}
class Weather {
    private String currentWeather;
    private String forecast;
    private int temperature;

public void updateWeather(String region) {
    // Simulated weather data based on the region
    switch (region) {
        case "Riyadh":
            currentWeather = "Sunny";
            forecast = "Clear skies for the next few days.";
            temperature = 40;
            break;
        case "Jeddah":
            currentWeather = "Humid";
            forecast = "Occasional clouds with a chance of rain.";
            temperature = 35;
            break;
        case "Eastern Province":
            currentWeather = "Windy";
            forecast = "Mild winds expected to continue.";
            temperature = 38;
            break;
        case "Abha":
            currentWeather = "Cool";
            forecast = "Clear skies with mild temperatures.";
            temperature = 25;
            break;
        case "Tabuk":
            currentWeather = "Mild";
            forecast = "Clear skies with cool evenings.";
            temperature = 28;
            break;
        case "Al-Jouf":
            currentWeather = "Dry";
            forecast = "Sunny with no significant changes expected.";
            temperature = 30;
            break;
        case "Hail":
            currentWeather = "Warm";
            forecast = "Clear skies with temperatures reaching peak levels.";
            temperature = 33;
            break;
        default:
            currentWeather = "Unknown";
            forecast = "No forecast available.";
            temperature = 0;
            break;
    }
}


    public void displayWeather() {
        System.out.println("Current Weather: " + currentWeather);
        System.out.println("Forecast: " + forecast);
        System.out.println("Temperature: " + temperature + "°C");
    }
}

// Base Class: Information
class Information {
    public String title, desc;
    public Date lastUpd;

    public Information(String title, String desc, Date lastUpd) {
        this.title = title;
        this.desc = desc;
        this.lastUpd = lastUpd;
    }

    public void showInfo() {
        System.out.println("Title: " + title);
        System.out.println("Description: " + desc);
        System.out.println("Last Updated: " + lastUpd);
    }
}

// Subclass: Emergency (Overriding)
class Emergency extends Information {
    public String contactNum;

    // Constructor for Emergency
    public Emergency(String title, String desc, Date lastUpd, String contactNum) {
        super(title, desc, lastUpd);
        this.contactNum = contactNum;
    }

    // Overridden method to display emergency-specific information
    @Override
    public void showInfo() {
        System.out.println("=== Emergency Information ===");
        super.showInfo(); // Call superclass's showInfo method
        System.out.println("Contact Number: " + contactNum);
    }

    // Method to display emergency contact numbers
    public void showContact() {
        System.out.println("=== Emergency Contact Numbers ===");
        System.out.println("Ambulance & Medical Emergencies: 997" + "\n"
                + "Police: 999" + "\n"
                + "Civil Defense (Fire & Rescue): 998" + "\n"
                + "Traffic Accidents: 993" + "\n"
                + "Electricity Emergency: 933" + "\n"
                + "Water & Sewage Issues: 020001744" + "\n"
                + "Public Transport Accidents (Trains & Buses): 8001249999" + "\n"
                + "Tourist Police: 939");
    }
}

// Subclass: Help (Overloading)
class Help extends Information {
    public String instructions;
    public String language;

    // Constructor for Help
    public Help(String title, String desc, Date lastUpd, String instructions, String language) {
        super(title, desc, lastUpd);
        this.instructions = instructions;
        this.language = language;
    }

    // Overloaded methods to display help information
    public void showHelp() {
        System.out.println("=== Help Information ===");
        System.out.println("Title: " + title);
        System.out.println("Description: " + desc);
        System.out.println("Instructions: " + instructions);
    }

    public void showHelp(String keyword) {
        System.out.println("=== Searching Help for Keyword: " + keyword + " ===");
        if (title.contains(keyword) || desc.contains(keyword)) {
            showHelp();
        } else {
            System.out.println("No help information found for keyword: " + keyword);
        }
    }

    public void showHelp(String keyword, boolean detailed) {
        System.out.println("=== Help Information with Details ===");
        if (title.contains(keyword) || desc.contains(keyword)) {
            showHelp();
            if (detailed) {
                System.out.println("Language: " + language);
                System.out.println("Last Updated: " + lastUpd);
            }
        } else {
            System.out.println("No help information found for keyword: " + keyword);
        }
    }

    // Method to display instructions in a specific language
    public void showHelpInLanguage() {
        if (language.equalsIgnoreCase("English")) {
            System.out.println("Welcome to SIYAH!");
            System.out.println("Instructions: Follow the guidelines for a safe and enjoyable experience.");
        } else if (language.equalsIgnoreCase("Arabic")) {
            System.out.println("مرحبًا بك في سيّاح!");
            System.out.println("التعليمات: اتبع الإرشادات لقضاء تجربة آمنة وممتعة.");
        } else {
            System.out.println("Sorry, this language is not supported. Only Arabic and English are available.");
        }
    }
}




