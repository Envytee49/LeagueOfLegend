package leagueoflegend.application.controller.detailcontroller;

import leagueoflegend.model.GeneralInfo;

public interface IDetailController<T extends GeneralInfo> {
    void setData(T data);
    void setUp();
}
