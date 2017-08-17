package pf.net.api.mail.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Email {

    private String from;
    private List<String> to;
    private List<String> cc;
    private String subject;
    private String message;
    private boolean isHtml;

    public Email() {
        this.to = new ArrayList<>();
        this.cc = new ArrayList<>();
    }

    public Email(String from, String toList, String subject, String message) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.to.addAll(Arrays.asList(splitByComma(toList)));
    }

    public Email(String from, String toList, String ccList, String subject, String message) {
        this();
        this.from = from;
        this.subject = subject;
        this.message = message;
        this.to.addAll(Arrays.asList(splitByComma(toList)));
        this.cc.addAll(Arrays.asList(splitByComma(ccList)));
    }

    private String[] splitByComma(String toMultiple) {
        return toMultiple.split(",");
    }

}
