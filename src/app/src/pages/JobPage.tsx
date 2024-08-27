import {
  Bar,
  Button,
  Card,
  CardHeader,
  Dialog,
  List,
  ListItemStandard,
  Title,
} from "@ui5/webcomponents-react";
import Filters from "../components/Filters";
import { BASE_URL } from "../utils/config";
import axios from "axios";
import { useEffect, useState } from "react";
import {
  useWorkModeState,
  useCompanySearchState,
  useJobSearchState,
  useSectorState,
} from "../store/Store";

export default function JobPage() {
  const [jobs, setJobs] = useState([]);
  const [dialogOpen, setDialogOpen] = useState<boolean>(false);
  const [selectedJob, setSelectedJob] = useState<string>("");
  const [selectedJobData, setSelectedJobData] = useState<any>({});
  const { selectedWorkMode } = useWorkModeState();
  const { selectedCompany } = useCompanySearchState();
  const { searchedJob } = useJobSearchState();
  const { selectedSector } = useSectorState();

  const fetchJobs = () => {
    const params: any = {};

    if (selectedWorkMode !== "All") {
      params.workMode = selectedWorkMode;
    }
    if (selectedCompany) {
      params.organizationName = selectedCompany;
    }
    if (searchedJob) {
      params.title = searchedJob;
    }
    if (selectedSector !== "All") {
      params.sector = selectedSector;
    }

    if (Object.keys(params).length === 0) {
      axios.get(`${BASE_URL}/jobs`).then((response) => {
        setJobs(response.data._embedded.jobs);
      });
    } else {
      axios
        .get(
          `${BASE_URL}/jobs/search/findAllByTitleOrOrganizationNameOrWorkModeOrSector`,
          { params }
        )
        .then((response) => {
          setJobs(response.data._embedded.jobs);
        });
    }
  };

  // const handleJobFetch = () => {

  // };

  const handleJobClick = (e: any) => {
    axios
      .get(
        `${BASE_URL}/jobs/${e.target.attributes["accessible-name"].textContent}`
      )
      .then((response) => {
        setSelectedJobData(response.data);
        console.log(response.data);
        setDialogOpen(true);
      });
  };

  const handleJobClose = () => {
    setDialogOpen(false);
    setSelectedJobData([]);
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  useEffect(() => {
    fetchJobs();
  }, [selectedWorkMode, selectedCompany, searchedJob, selectedSector]);

  return (
    <>
      <div className="p-2">
        <Filters />
      </div>
      <div className="p-1">
        {jobs.map((job) => (
          <Card
            header={
              <CardHeader
                subtitleText={job["organizationName"]}
                titleText={job["title"]}
                onClick={handleJobClick}
              />
            }
            style={{
              width: "300px",
            }}
            className="p-4"
            key={job["id"]}
          >
            <List>
              <ListItemStandard description={job["description"]}>
                Job Description
              </ListItemStandard>
              <ListItemStandard description={job["sector"]}>
                Sector
              </ListItemStandard>
              <ListItemStandard description={job["workMode"]}>
                Work Mode
              </ListItemStandard>
              <ListItemStandard>
                <Button
                  onClick={handleJobClick}
                  design="Attention"
                  accessibleName={job["id"]}
                  className="item-center flex"
                >
                  Details
                </Button>
              </ListItemStandard>
            </List>
          </Card>
        ))}
      </div>
      {dialogOpen ? (
        <Dialog
          open={dialogOpen}
          header={<Bar startContent={<Title level="H3">Details</Title>} />}
          footer={
            <Bar
              design="Footer"
              endContent={<Button onClick={handleJobClose}>Close</Button>}
            />
          }
        >
          <div key={selectedJobData["id"]}>
            <p>{selectedJobData["title"]}</p>
            <p>{selectedJobData["description"]}</p>
            <p>{selectedJobData["organizationName"]}</p>
            <p>{selectedJobData["sector"]}</p>
            <p>{selectedJobData["workMode"]}</p>
          </div>
        </Dialog>
      ) : (
        <></>
      )}
    </>
  );
}
