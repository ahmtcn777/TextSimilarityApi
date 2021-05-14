package com.otosor.textSearch.Services;

import com.otosor.textSearch.Entity.Text;
import com.otosor.textSearch.Entity.Writer;
import com.otosor.textSearch.Exceptions.CustomException;
import com.otosor.textSearch.Repository.TextRepository;
import com.otosor.textSearch.Repository.WriterRepository;
import com.otosor.textSearch.VO.TextVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@Service
public class TextService {
    @Autowired
    TextRepository textRepository;

    @Autowired
    WriterRepository writerRepository;

    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    public Text saveText(TextVO textVO) throws CustomException {
        Text text = new Text();
        try {
            text.setText(textVO.getText().getBytes(StandardCharsets.UTF_8));
            text.setWriterId(textVO.getWriterId());
            textRepository.save(text);
        } catch (Exception ex){
            throw new CustomException(ex.getMessage());
        }
        return text;
    }

    public String findWriter(TextVO textVO) {
        List<Writer> writers = writerRepository.findAll();
        final double[] max = {0.0};
        final Writer[] findWriter = {null};
        writers.forEach(writer -> {
            writer.getTexts().forEach(text -> {
                double similarity = similarity(new String(text.getText(), StandardCharsets.UTF_8), textVO.getText());
                if(max[0] < similarity) {
                    findWriter[0] = writer;
                    max[0] = similarity;
                }
            });
        });
        return findWriter[0].getName() + " " +  findWriter[0].getSurname() + " similarity is %" + Math.round(max[0]*100);
    }

    /**
            * Calculates the similarity (a number within 0 and 1) between two strings.
            */
    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
    /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}
