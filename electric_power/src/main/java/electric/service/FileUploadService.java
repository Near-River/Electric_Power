package electric.service;

import electric.base.dao.DaoSupport;
import electric.entity.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author near on 2016/3/15.
 */
@Service
@Transactional
public interface FileUploadService extends DaoSupport<FileUpload> {

    List<FileUpload> searchByCondition(FileUpload model);

    void saveUploadFile(List<Object[]> fileInfo, FileUpload model);

    List<FileUpload> searchByQueryString(String queryString, FileUpload model);

}
