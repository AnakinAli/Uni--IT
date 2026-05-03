package menaged_beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dao.Database;
import entities.Message;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class MessagesFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private Database db;

    private String filterText;
    private String filterBy;

    private String[] filterByOptions = {
            "име",
            "част от име",
            "имейл",
            "част от имейл"
    };

    private List<Message> filteredMessages = new ArrayList<Message>();

    @PostConstruct
    public void init() {
        filteredMessages = db.getMessages();
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }

    public String getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public String[] getFilterByOptions() {
        return filterByOptions;
    }

    public List<Message> getFilteredMessages() {
        return filteredMessages;
    }

    public String filter() {
        if (filterText == null || filterText.trim().isEmpty()) {
            filteredMessages = db.getMessages();
            return "";
        }

        if (filterBy == null || filterBy.trim().isEmpty()) {
            filteredMessages = db.getMessages();
            return "";
        }

        if (filterBy.equals(filterByOptions[0])) {
            filteredMessages = db.getMessagesFilteredByName(filterText);
        }

        if (filterBy.equals(filterByOptions[1])) {
            filteredMessages = db.getMessagesFilteredByNameWithLike(filterText);
        }

        if (filterBy.equals(filterByOptions[2])) {
            filteredMessages = db.getMessagesFilteredByEmail(filterText);
        }

        if (filterBy.equals(filterByOptions[3])) {
            filteredMessages = db.getMessagesFilteredByEmailWithLike(filterText);
        }

        return "";
    }

    public String deleteMessage(int id) {
        db.deleteMessage(id);

        if (filterText == null || filterText.trim().isEmpty()
                || filterBy == null || filterBy.trim().isEmpty()) {
            filteredMessages = db.getMessages();
        } else {
            filter();
        }

        return "";
    }
}