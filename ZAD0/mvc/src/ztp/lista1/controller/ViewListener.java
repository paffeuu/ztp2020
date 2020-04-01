package ztp.lista1.controller;

import ztp.lista1.view.ViewActionType;

public interface ViewListener {
    void actionPerformed(ViewActionType viewActionType, Object object, String data);
}
