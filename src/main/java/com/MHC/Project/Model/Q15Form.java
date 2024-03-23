package com.MHC.Project.Model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;


@Document(collection = "Q15Form")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Q15Form {

    @Id
    private String id;
    private Map<String, Object> location;
    private Map<String, Object> activity;
    private Map<String, Object> q15;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Map<String, Object> getLocation() {
        return location;
    }

    public void setLocation(Map<String, Object> location) {
        this.location = location;
    }

    public Map<String, Object> getActivity() {
        return activity;
    }

    public void setActivity(Map<String, Object> activity) {
        this.activity = activity;
    }

    public Map<String, Object> getQ15() {
        return q15;
    }

    public void setQ15(Map<String, Object> q15) {
        this.q15 = q15;
    }

    // ************ Constructor Method ************\\

    public Q15Form(String id, Map<String, Object> location, Map<String, Object> activity, Map<String, Object> q15) {
        this.id = id;
        this.location = location;
        this.activity = activity;
        this.q15 = q15;
    }

    public static Q15Form build(String id, Map<String, Object> location, Map<String, Object> activity, Map<String, Object> q15) {
        return new Q15Form(id, location, activity, q15);
    }

    public void update(Q15Form formRequest) {
        this.setLocation(formRequest.getLocation());
        this.setActivity(formRequest.getActivity());
        this.setQ15(formRequest.getQ15());
    }
}
