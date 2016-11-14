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
import java.util.List;

@RestController
@RequestMapping("/api/processes")
public class ProcessesREST {

    @Autowired
    ProcessService processService;

    @GetMapping("/{processId}")
    public Process processGet(@PathVariable int processId) {
        return processService.getProcessById(processId);
    }

//    @PostMapping("")
//    public ResponseEntity<Void> processesPost(@RequestBody Node detachedNode, UriComponentsBuilder ucBuilder) {
//        Process persistentProcess = processService.createProcess(detachedNode);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/processes/{id}").buildAndExpand(persistentProcess.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
    @PostMapping("")
    public ResponseEntity<Void> processesPost(
            @RequestParam("nodeId") int nodeId,
            UriComponentsBuilder ucBuilder) {
        Process persistentProcess = processService.createProcess(nodeId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                ucBuilder
                    .path("/api/processes/{id}")
                    .buildAndExpand(persistentProcess.getId())
                    .toUri()
        );
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{processId}/nodes/{nodeId}")
    public ResponseEntity<Void> nodesPut(@PathVariable int processId, @PathVariable int nodeId) {
        try {
            processService.processAddNode(processId, nodeId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{processId}/nodes")
    public List<Node> processNodesGet(@PathVariable int processId) {
        return processService.getProcessPath(processId);
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
