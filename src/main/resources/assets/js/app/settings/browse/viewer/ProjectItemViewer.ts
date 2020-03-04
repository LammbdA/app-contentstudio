import {SpanEl} from 'lib-admin-ui/dom/SpanEl';
import {NamesView} from 'lib-admin-ui/app/NamesView';
import {Element} from 'lib-admin-ui/dom/Element';
import {SettingsItemViewer} from './SettingsItemViewer';
import {ProjectViewItem} from '../../view/ProjectViewItem';
import {i18n} from 'lib-admin-ui/util/Messages';

export class ProjectItemViewer
    extends SettingsItemViewer {

    constructor(className?: string) {
        super('project-item-viewer ' + (className || ''));
    }

    doLayout(item: ProjectViewItem) {
        super.doLayout(item);

        if (!item) {
            return;
        }

        const namesView: NamesView = this.namesAndIconView.getNamesView();

        const displayNameEl: Element = new SpanEl('display-name').setHtml(item.getDisplayName());
        const nameEl: Element = new SpanEl('name').setHtml(`(${item.getName()})`);

        namesView.setMainNameElements([displayNameEl, nameEl]);
    }

    resolveDisplayName(item: ProjectViewItem): string {
        return item.getDisplayName();
    }

    resolveSubName(item: ProjectViewItem, relativePath: boolean = false): string {
        return item.getDescription() || `<${i18n('text.noDescription')}>`;
    }

    resolveIconClass(item: ProjectViewItem): string {
        return `icon-large ${item.getIconClass()}`;
    }

}

