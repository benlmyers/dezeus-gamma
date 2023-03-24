package com.benmyers.dezeus.core.rule;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import com.benmyers.dezeus.core.error.RuleIndexingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RulesIndex {

    private static String basePath = "rules";
    private Map<String, RuleIndex> rules = new HashMap<>();

    public class RuleIndex {

        private String id;

        public RuleIndex(String id) {
            this.id = id;
        }
    }

    public Map<String, RuleIndex> getRules() {
        return rules;
    }

    public void write() throws RuleIndexingException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        File file = new File(basePath + "/index.json");
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            Files.write(file.toPath(), json.getBytes());
        } catch (Exception e) {
            throw new RuleIndexingException();
        }
    }

    public static RulesIndex read() throws RuleIndexingException {
        File file = new File(basePath + "/index.json");
        try {
            String json = new String(Files.readAllBytes(file.toPath()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(json, RulesIndex.class);
        } catch (Exception e) {
            throw new RuleIndexingException();
        }
    }
}