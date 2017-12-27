import { ProjectCameraPage } from './app.po';

describe('project-camera App', () => {
  let page: ProjectCameraPage;

  beforeEach(() => {
    page = new ProjectCameraPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
