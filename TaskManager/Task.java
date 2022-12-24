import java.time.LocalDate;

public class Task {
    private int id;
    private String name, info;
    private LocalDate creationDate, completeDate;
    private Boolean done;

    public Task(
        int id,
        String name,
        String info,
        LocalDate creationDate,
        LocalDate completeDate
    ) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.creationDate = creationDate;
        this.completeDate = completeDate;
        this.done = completeDate.isBefore(creationDate);
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getInfo() {
        return this.info;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public LocalDate getCompleteDate() {
        return completeDate;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public Boolean isDone() {
        return this.done;
    }
}
