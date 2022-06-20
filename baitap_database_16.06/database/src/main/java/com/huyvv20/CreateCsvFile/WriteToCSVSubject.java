package com.huyvv20.CreateCsvFile;

import com.huyvv20.object.subject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WriteToCSVSubject {
    static Random rd = new Random();
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPARATOR = "\n";
    static String[] subjects = {"Algebra",
            "General Physics",
            "Introduction to Intormation Technology",
            "Signals and systems",
            "Data structures and algorithms",
            "Discrete Mathematics",
            "Database",
            "Computer Network",
            "Database Management Systems",
            "Data Mining",
            "Web development",
            "Marxist-Leninist Philosophy",
            "Marxist-Leninist Political Economy",
            "Scientific Socialism",
            "History of Vietnam Communist Party",
            "Ho Chi Minh's Ideology",
            "Physical Education",
            "National Defence Education",
            "Calculus",
            "Probability and Statistics",
            "Introduction to AI",
            "Modeling and Optimization for Machine Learning",
            "Foundations of Computer Systems",
            "Programming for Data Engineering with Python",
            "Object-oriented Programming",
            "Software Engineering",
            "Foundations of Artificial Intelligence",
            "Machine Learning",
            "Deep learning",
            "Natural language processing",
            "Visual processing and analytics",
            "Data mining and analytics",
            "AI system development practice",
            "Big Data Techniques and Technologies",
            "Scientific Seminars",
            "Cognition; language and thought",
            "Probabilistic Graphical Models",
            "Reinforcement Learning and Planning",
            "Programming with GPU",
            "AI applications in natural language",
            "Speech processing",
            "Human-AI Interaction",
            "AI applications in medicine",
            "Computational Neuroscience and Applications",
            "Bioinformatics",
            "AI Robotics",
            "AI applications in Engineering",
            "AI applications in Environment",
            "Knowledge-based Systems",
            "Hardware Design for Deep Learning",
            "Electronics Devices",
            "Analog Electronics",
            "Digital Electronics",
            "Electromagnetics Engineering",
            "Digital Signal Processing",
            "Analog Electronic Engineering Practice",
            "Digital Electronic Engineering Practice",
            "Communications",
            "Control Engineering",
            "Optical Communication",
            "Introduction to Signal Processing for Multimedia Systems",
            "Introduction to High Frequency Circuits and Systems",
            "Digital Communications and Coding Theory",
            "Mobile Communication Systems",
            "Embedded Computing System Design",
            "Digital Control Systems",
            "Signal Processing and Bio -medical Imaging",
            "Bioelectromagnetism",
            "Wireless Communications",
            "Programming for Mobile Devices",
            "Programming for Mobile Devices",
            "Antenna Techniques",
            "Satellite Communication",
            "HF Techniques",
            "Real -time Embedded Systes",
            "Signal Processing Methods",
            "Communication Network Administrator",
            "Modern Physics",
            "Optimization",
            "Introduction to Management Science",
            "State and Law",
            "Micro Economics",
            "Macro Economics",
            "rinciples Of Marketing",
            "System Design Project",
            "Electronics and Communication Practice",
            "Mathematics for Economics",
            "Monetary and Financial Theories 1",
            "Accounting Principles",
            "Social Security",
            "Insurance Principles",
            "English for Insurance",
            "Business Insurance 1",
            "Business Insurance 2",
            "Social Insurance 1",
            "Social Insurance 2",
            "Insurance Business Administration 1",
            "Insurance Business Administration 2",
            "Insurance Statistics",
            "Business Communication and Negotiation",
            "Business Insurance Electives",
            "Risk Management in Insurance Electives",
            "Financial Analysis",
            "Corprorate Finance",
            "Investement in Insurance",
            "Managing Social Insurance cards and books Electives",
            "Corporate Culture",
            "Reinsurance",
            "Insurance Adjusting",
            "Accounting for Insurance Enterprise",
            "Laws in Insurance Business",
            "Insurance Business Analysis",
            "Adjusting Insurance Electives",
            "Social security Law",
            "Labour Protection",
            "Social Insurance Accounting",
            "Social Insurance Electives",
            "Social Insurance Management Electives",
            "Unemploment Insurance Electives",
            "Public Relation Research",
            "Advertising Management",
            "Agency Management",
            "Creative Strategy in Communication",
            "Brand Management",
            "Media for Public Relations",
            "Public Relations Internship Project",
            "Sales Promotion",
            "Price Management",
            "Trade Mark Valuation",
            "Public Marketing",
            "Service Marketing",
            "Digital Marketing",
            "Organizational Behavior",
            "Human Resource Management",
            "Event Planning",
            "Crisis Management",
            "Legal and Ethics Foundation of Communications",
            "Modern Journalism",
            "Sponsorship Management",
            "Brand Identity",
            "Principles of Aesthetic",
            "Public Speaking",
            "Principles of Psychology 1",
            "Investment Project Appraisal",
            "Investment Project Accounting",
            "Accounting for Banking",
            "Strategic Management",
            "Financial Management",
            "Public Finance",
            "Public Finance",
            "Commercial Bank",
            "Stock Market",
            "International Economic Integration",
            "Accounting Principles",
            "Global Supply Chain Management",
            "Principles of Logistics Management",
            "Commercial Business",
            "Logistics Business",
            "Trade in Enterprises",
            "Management Information Systems in Logistics",
            "International Trade Management",
            "Transportation and Freight Forwarding in Foreign Trade",
            "Business Communication and Negotiation",
            "International Business",
            "Electronic Commerce",
            "Customs Practice",
            "Major Research Paper",
            "Warehouse Management",
            "Digital Marketing",
            "Economics and Technical Norms for Enterprises",
            "Operations Management in Logistics",
            "Laws on International Maritime",
            "Research Methods for Socio - Economic",
            "Logistics in Enterprise",
            "Multi-modal Transport Management",
            "E - Logistics",
            "Logistics Infrastructure",
            "International Busines Services",
            "Import-Export Practice",
            "Payment and Credit in International Trade",
            "Product Management",
            "Managerial Accounting",
            "Trade Economics 1",
            "Logistics Business",
            "Merchandise Packaging and Branding",
            "Internaional Trade Management 1",
            "Business Communication and Negotiation",
            "Electronic Commerce",
            "Customs Practice",
            "International Business",
            "Major Research Paper",
            "Job analysis and Performance Management",
            "Compensation Management",
            "Strategic Management",
            "Strategic Management",
            "Labor Economics",
            "Population and Development",
            "Labour Psychology",
            "Human Resource Acquiring",
            "Industrial Relation/Labour Relation",
            "Industrial Relation/Labour Relation 2",
            "Industrial Relation/Labour Relation 3"};
    public static void main(String[] args) {
        String fileName = "E:/masterdev/baitap_database_16.06/database/data/subject.csv";
        writeCsvFile(fileName);
    }

    public static void writeCsvFile(String fileName){
        List<subject> subjectList = new ArrayList<>();
        for(int i=1 ; i<201; i++){
            subject subject = new subject();
            subject.setId_subject(i);
            subject.setSubject_name(subjects[i]);
            subject.setCredit((int) (Math.random()*((5-2)+1) + 2));
            subjectList.add(subject);
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            // Write the CSV file header
            //fileWriter.append(FILE_HEADER);

            // Add a new line separator after the header
            //fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new Country object list to the CSV file
            for (subject subject : subjectList) {
                fileWriter.append(String.valueOf(subject.getId_subject()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(subject.getSubject_name());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(subject.getCredit()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }
}
