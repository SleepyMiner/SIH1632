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
} from "../store/Store";

export default function JobPage() {
  const [jobs, setJobs] = useState([]);
  const [dialogOpen, setDialogOpen] = useState<boolean>(false);
  const [selectedJob, setSelectedJob] = useState<string>("");
  const [selectedJobData, setSelectedJobData] = useState<any>({});
  const { selectedWorkMode } = useWorkModeState();
  const { selectedCompany } = useCompanySearchState();
  const { searchedJob } = useJobSearchState();

  const fetchJobs = () => {
    if (
      selectedCompany != "" &&
      searchedJob != "" &&
      selectedWorkMode != "All"
    ) {
    } else {
      axios.get(`${BASE_URL}/jobs`).then((response) => {
        setJobs(response.data._embedded.jobs);
      });
    }
  };

  const handleJobFetch = () => {
    axios.get(`${BASE_URL}/jobs/${selectedJob}`).then((response) => {
      console.log(response.data);
      setSelectedJobData(response.data);
    });
  };

  const handleJobClick = (e: any) => {
    setSelectedJob(e.target.attributes["accessible-name"].textContent);
    setDialogOpen(true);
    handleJobFetch();
  };

  const handleJobClose = () => {
    setDialogOpen(false);
    setSelectedJobData([]);
    handleJobFetch();
  };

  const fetchJobOnType = () => {
    if (selectedWorkMode != "All") {
      axios
        .get(
          `${BASE_URL}/jobs/search/findAllByTitleOrOrganizationNameOrWorkModeOrSector?workMode=${selectedWorkMode}`
        )
        .then((response) => {
          setJobs(response.data._embedded.jobs);
          console.log(response.data);
        });
    } else {
      fetchJobs();
    }
  };

  const fetchByCompany = () => {
    if (selectedCompany != "" && searchedJob != "") {
      axios
        .get(
          `${BASE_URL}/jobs/search/findAllByOrganizationNameContaining?organizationName=${selectedCompany}`
        )
        .then((response) => {
          setJobs(response.data._embedded.jobs);
        });
    } else {
      fetchJobs();
    }
  };

  const fetchByJob = () => {
    if (searchedJob != "") {
      axios
        .get(
          `${BASE_URL}/jobs/search/findAllByTitleContaining?title=${searchedJob}`
        )
        .then((response) => {
          setJobs(response.data._embedded.jobs);
        });
    } else {
      fetchJobs();
    }
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  useEffect(() => {
    fetchJobOnType();
  }, [selectedWorkMode]);

  useEffect(() => {
    fetchByCompany();
  }, [selectedCompany]);

  useEffect(() => {
    fetchByJob();
  }, [searchedJob]);

  return (
    <>
      <div className="p-2">
        <Filters />
      </div>
      <div className="p-4">
        {jobs.map((job) => (
          <Card
            header={
              <CardHeader
                subtitleText={
                  !job["organizationName"] ? "ADMIN" : job["organizationName"]
                }
                titleText={job["title"]}
                // additionalText={`Details: ${(<a href="#">Go to Details</a>)}`}
                onClick={handleJobClick}
              />
            }
            style={{
              width: "300px",
            }}
            className="p-8"
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
          <p>{selectedJobData["title"]}</p>
        </Dialog>
      ) : (
        <></>
      )}
    </>
  );
}
