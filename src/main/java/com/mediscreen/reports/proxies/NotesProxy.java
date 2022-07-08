package com.mediscreen.reports.proxies;

import com.mediscreen.reports.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "notes", url = "notes:8082")
public interface NotesProxy {

    @GetMapping("/{patientId}/note/list")
    List<Note> getAllNotes(@PathVariable Integer patientId);

}
