package ua.legalist.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ua.legalist.model.Node;
import ua.legalist.service.ProcessService;
import ua.legalist.model.Process;

@RestController
@RequestMapping("/api/processes")
public class ProcessesREST {

    @Autowired
    ProcessService processService;

    @GetMapping("{processId}")
    public Process processGet(@PathVariable int processId) {
        return processService.getProcessById(processId);
    }

    @PostMapping("/")
    public ResponseEntity<Void> processesPost(@RequestBody Node node, UriComponentsBuilder ucBuilder) {
        Process process = processService.createProcess(node);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/process/{id}").buildAndExpand(process.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{processId}/nodes/{nodeId}")
    public ResponseEntity<Void> nodesPut(@RequestParam("processId") int processId,
            @RequestParam("nodeId") int nodeId) {
        processService.processAddNode(processId, nodeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{processId}")
    public ResponseEntity<Void> processesPut(@RequestBody Process process, @PathVariable int processId) {
        processService.update(process);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{processId}")
    public ResponseEntity<Void> processDelete(@PathVariable int processId) {
        processService.deleteById(processId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
